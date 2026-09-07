package com.example.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.PharmacyData
import com.example.model.CartItem
import com.example.model.MedicineCategory
import com.example.model.MedicineItem
import com.example.model.MedicineReminder
import com.example.model.PharmacyInfo
import com.example.model.PrescriptionOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

enum class AppTab(val titleAr: String, val titleEn: String) {
    CATALOG("الأدوية والمنتجات", "Store"),
    PRESCRIPTION("طلب روشتة", "Prescription"),
    REMINDERS("مواعيد الدواء", "Reminders"),
    ABOUT("عن الصيدلية", "About & Info")
}

data class PharmacyUiState(
    val pharmacyInfo: PharmacyInfo = PharmacyData.info,
    val selectedTab: AppTab = AppTab.CATALOG,
    val searchQuery: String = "",
    val selectedCategory: MedicineCategory = MedicineCategory.ALL,
    val cartItems: List<CartItem> = emptyList(),
    val selectedMedicine: MedicineItem? = null,
    val isCartOpen: Boolean = false,
    val prescriptionOrders: List<PrescriptionOrder> = emptyList(),
    val reminders: List<MedicineReminder> = listOf(
        MedicineReminder(
            id = "rem_1",
            medicineName = "بانادول إكسترا",
            dosage = "قرص واحد",
            timeFormatted = "02:00 م",
            isTaken = true,
            notes = "بعد الغداء مباشرة"
        ),
        MedicineReminder(
            id = "rem_2",
            medicineName = "أوميجا 3 زيت سمك",
            dosage = "كبسولة واحدة",
            timeFormatted = "08:00 م",
            isTaken = false,
            notes = "مع العشاء"
        )
    ),
    val orderSuccessMessage: String? = null
)

class PharmacyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PharmacyUiState())
    val uiState: StateFlow<PharmacyUiState> = _uiState.asStateFlow()

    private val _allMedicines = MutableStateFlow(PharmacyData.sampleMedicines)

    val filteredMedicines: StateFlow<List<MedicineItem>> = combine(
        _allMedicines,
        _uiState
    ) { medicines, state ->
        medicines.filter { item ->
            val matchesCategory = (state.selectedCategory == MedicineCategory.ALL || item.category == state.selectedCategory)
            val query = state.searchQuery.trim().lowercase()
            val matchesQuery = query.isEmpty() ||
                item.nameAr.lowercase().contains(query) ||
                item.nameEn.lowercase().contains(query) ||
                item.descriptionAr.lowercase().contains(query) ||
                item.indicationAr.lowercase().contains(query)
            matchesCategory && matchesQuery
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        PharmacyData.sampleMedicines
    )

    fun selectTab(tab: AppTab) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun onCategorySelected(category: MedicineCategory) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun openMedicineDetails(item: MedicineItem) {
        _uiState.value = _uiState.value.copy(selectedMedicine = item)
    }

    fun closeMedicineDetails() {
        _uiState.value = _uiState.value.copy(selectedMedicine = null)
    }

    fun setCartOpen(isOpen: Boolean) {
        _uiState.value = _uiState.value.copy(isCartOpen = isOpen)
    }

    fun addToCart(medicine: MedicineItem) {
        val currentCart = _uiState.value.cartItems.toMutableList()
        val index = currentCart.indexOfFirst { it.medicine.id == medicine.id }
        if (index >= 0) {
            val existing = currentCart[index]
            currentCart[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            currentCart.add(CartItem(medicine = medicine, quantity = 1))
        }
        _uiState.value = _uiState.value.copy(cartItems = currentCart)
    }

    fun decreaseCartQuantity(medicineId: String) {
        val currentCart = _uiState.value.cartItems.toMutableList()
        val index = currentCart.indexOfFirst { it.medicine.id == medicineId }
        if (index >= 0) {
            val existing = currentCart[index]
            if (existing.quantity > 1) {
                currentCart[index] = existing.copy(quantity = existing.quantity - 1)
            } else {
                currentCart.removeAt(index)
            }
            _uiState.value = _uiState.value.copy(cartItems = currentCart)
        }
    }

    fun removeFromCart(medicineId: String) {
        val currentCart = _uiState.value.cartItems.filterNot { it.medicine.id == medicineId }
        _uiState.value = _uiState.value.copy(cartItems = currentCart)
    }

    fun clearCart() {
        _uiState.value = _uiState.value.copy(cartItems = emptyList())
    }

    fun submitOrder(context: Context, customerName: String, customerPhone: String, deliveryAddress: String, notes: String) {
        val items = _uiState.value.cartItems
        if (items.isEmpty()) return

        val totalYer = items.sumOf { it.medicine.priceYer * it.quantity }
        val orderSummary = buildString {
            append("طلب أدوية جديد من تطبيق صيدلية ماس كير:\n")
            append("العميل: $customerName\n")
            append("الهاتف: $customerPhone\n")
            append("عنوان التوصيل بصنعاء: $deliveryAddress\n")
            if (notes.isNotBlank()) append("ملاحظات: $notes\n")
            append("-------------------\n")
            items.forEach {
                append("- ${it.medicine.nameAr} × ${it.quantity} = ${it.medicine.priceYer * it.quantity} ريال\n")
            }
            append("-------------------\n")
            append("الإجمالي: $totalYer ريال يمني")
        }

        // WhatsApp action
        openWhatsApp(context, orderSummary)
        clearCart()
        _uiState.value = _uiState.value.copy(
            isCartOpen = false,
            orderSuccessMessage = "تم إرسال طلبك بنجاح لصيدلية ماس كير! سيتم التواصل معك للتسليم."
        )
    }

    fun submitPrescription(
        context: Context,
        patientName: String,
        phone: String,
        address: String,
        notes: String,
        isUrgent: Boolean,
        hasPhoto: Boolean
    ) {
        val order = PrescriptionOrder(
            id = UUID.randomUUID().toString().take(8),
            patientName = patientName,
            phone = phone,
            address = address,
            notes = notes,
            prescriptionPhotoName = if (hasPhoto) "prescription_image.jpg" else null,
            isUrgent = isUrgent
        )

        val updated = listOf(order) + _uiState.value.prescriptionOrders
        _uiState.value = _uiState.value.copy(
            prescriptionOrders = updated,
            orderSuccessMessage = "تم استلام الروشتة الطبية بنجاح! يقوم صيدلاني ماس كير بمراجعتها الآن."
        )

        // Also give option to dispatch via WhatsApp directly
        val message = "طلب روشتة طبية - صيدلية ماس كير:\nالمريض: $patientName\nالهاتف: $phone\nالعنوان: $address\nملاحظات: $notes\n${if (isUrgent) "⚠️ حالة عاجلة" else "حالة عادية"}"
        openWhatsApp(context, message)
    }

    fun dismissSuccessMessage() {
        _uiState.value = _uiState.value.copy(orderSuccessMessage = null)
    }

    fun addReminder(name: String, dosage: String, time: String, notes: String) {
        val newReminder = MedicineReminder(
            id = UUID.randomUUID().toString(),
            medicineName = name,
            dosage = dosage,
            timeFormatted = time,
            notes = notes,
            isTaken = false
        )
        _uiState.value = _uiState.value.copy(reminders = _uiState.value.reminders + newReminder)
    }

    fun toggleReminderTaken(id: String) {
        val updated = _uiState.value.reminders.map {
            if (it.id == id) it.copy(isTaken = !it.isTaken) else it
        }
        _uiState.value = _uiState.value.copy(reminders = updated)
    }

    fun deleteReminder(id: String) {
        val updated = _uiState.value.reminders.filterNot { it.id == id }
        _uiState.value = _uiState.value.copy(reminders = updated)
    }

    // Direct Intent Handlers
    fun callPharmacy(context: Context) {
        try {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${PharmacyData.info.phone}")
            }
            context.startActivity(phoneIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "رقم الصيدلية: ${PharmacyData.info.phone}", Toast.LENGTH_LONG).show()
        }
    }

    fun openWhatsApp(context: Context, text: String = "مرحباً صيدلية ماس كير، استفسار بخصوص الأدوية والخدمات") {
        try {
            val url = "https://wa.me/967785665658?text=${Uri.encode(text)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "واتساب صيدلية ماس كير: 785665658", Toast.LENGTH_LONG).show()
        }
    }

    fun openLocationInMap(context: Context) {
        try {
            val mapUri = Uri.parse("geo:15.3418,44.1983?q=" + Uri.encode("صيدلية ماس كير شارع عمان تقاطع شارع الجزائر صنعاء"))
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            Toast.makeText(context, PharmacyData.info.address, Toast.LENGTH_LONG).show()
        }
    }
}
