package com.example.model

data class PharmacyInfo(
    val nameAr: String = "صيدلية ماس كير",
    val nameEn: String = "MAS CARE PHARMACY",
    val sloganAr: String = "رعاية تليق بك",
    val sloganEn: String = "Care you deserve",
    val address: String = "شارع عمان تقاطع شارع الجزائر - صنعاء",
    val phone: String = "785665658",
    val workingHours: String = "مفتوح على مدار 24 ساعة (طوال أيام الأسبوع)",
    val deliveryAvailable: Boolean = true,
    val whatsappNumber: String = "+967785665658"
)

enum class MedicineCategory(val titleAr: String, val titleEn: String) {
    ALL("الكل", "All"),
    MEDICINES("أدوية ومسكنات", "Medicines"),
    VITAMINS("فيتامينات ومكملات", "Vitamins"),
    MOM_AND_BABY("رعاية الأم والطفل", "Baby & Mom"),
    SKINCARE("العناية بالبشرة", "Skincare"),
    DEVICES("أجهزة ومستلزمات", "Medical Devices"),
    FIRST_AID("إسعافات أولية", "First Aid")
}

data class MedicineItem(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val category: MedicineCategory,
    val dosageForm: String, // أقراص، كبسولات، شراب، كريم، بخاخ
    val priceYer: Int, // Price in Yemeni Rials
    val descriptionAr: String,
    val indicationAr: String,
    val inStock: Boolean = true,
    val requiresPrescription: Boolean = false,
    val manufacturer: String = "شركة معتمدة"
)

data class CartItem(
    val medicine: MedicineItem,
    val quantity: Int
)

data class PrescriptionOrder(
    val id: String,
    val patientName: String,
    val phone: String,
    val address: String,
    val notes: String,
    val prescriptionPhotoName: String?,
    val isUrgent: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "قيد المراجعة والتجهيز"
)

data class MedicineReminder(
    val id: String,
    val medicineName: String,
    val dosage: String,
    val timeFormatted: String,
    val isTaken: Boolean = false,
    val notes: String = ""
)
