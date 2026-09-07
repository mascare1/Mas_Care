package com.example.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalPharmacy
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.MedicineCategory
import com.example.ui.components.CartBottomSheet
import com.example.ui.components.MedicineDetailsDialog
import com.example.ui.components.MedicineItemCard
import com.example.ui.components.PharmacyHeaderCard
import com.example.ui.theme.MasTealAccent
import com.example.ui.theme.MasTealDark
import com.example.ui.theme.MasTealLight
import com.example.ui.theme.MasTealPrimary
import com.example.viewmodel.AppTab
import com.example.viewmodel.PharmacyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: PharmacyViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val medicines by viewModel.filteredMedicines.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.orderSuccessMessage) {
        uiState.orderSuccessMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissSuccessMessage()
        }
    }

    // Force RTL layout direction for Arabic-first pharmacy experience
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mas_care_logo),
                                contentDescription = "شعار ماس كير",
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = uiState.pharmacyInfo.nameAr,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    text = uiState.pharmacyInfo.nameEn,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MasTealLight,
                                        letterSpacing = 1.sp
                                    )
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { viewModel.setCartOpen(true) },
                            modifier = Modifier.testTag("top_cart_button")
                        ) {
                            BadgedBox(
                                badge = {
                                    val totalItems = uiState.cartItems.sumOf { it.quantity }
                                    if (totalItems > 0) {
                                        Badge(
                                            containerColor = Color(0xFFD32F2F),
                                            contentColor = Color.White
                                        ) {
                                            Text("$totalItems")
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "السلة",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MasTealPrimary,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding(),
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp
                ) {
                    NavigationBarItem(
                        selected = uiState.selectedTab == AppTab.CATALOG,
                        onClick = { viewModel.selectTab(AppTab.CATALOG) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.selectedTab == AppTab.CATALOG) Icons.Filled.LocalPharmacy else Icons.Outlined.LocalPharmacy,
                                contentDescription = AppTab.CATALOG.titleAr
                            )
                        },
                        label = { Text(AppTab.CATALOG.titleAr, maxLines = 1) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MasTealPrimary,
                            selectedTextColor = MasTealPrimary,
                            indicatorColor = MasTealLight
                        ),
                        modifier = Modifier.testTag("tab_catalog")
                    )

                    NavigationBarItem(
                        selected = uiState.selectedTab == AppTab.PRESCRIPTION,
                        onClick = { viewModel.selectTab(AppTab.PRESCRIPTION) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.selectedTab == AppTab.PRESCRIPTION) Icons.Filled.Description else Icons.Outlined.Description,
                                contentDescription = AppTab.PRESCRIPTION.titleAr
                            )
                        },
                        label = { Text(AppTab.PRESCRIPTION.titleAr, maxLines = 1) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MasTealPrimary,
                            selectedTextColor = MasTealPrimary,
                            indicatorColor = MasTealLight
                        ),
                        modifier = Modifier.testTag("tab_prescription")
                    )

                    NavigationBarItem(
                        selected = uiState.selectedTab == AppTab.REMINDERS,
                        onClick = { viewModel.selectTab(AppTab.REMINDERS) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.selectedTab == AppTab.REMINDERS) Icons.Filled.Alarm else Icons.Outlined.Alarm,
                                contentDescription = AppTab.REMINDERS.titleAr
                            )
                        },
                        label = { Text(AppTab.REMINDERS.titleAr, maxLines = 1) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MasTealPrimary,
                            selectedTextColor = MasTealPrimary,
                            indicatorColor = MasTealLight
                        ),
                        modifier = Modifier.testTag("tab_reminders")
                    )

                    NavigationBarItem(
                        selected = uiState.selectedTab == AppTab.ABOUT,
                        onClick = { viewModel.selectTab(AppTab.ABOUT) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.selectedTab == AppTab.ABOUT) Icons.Filled.Info else Icons.Outlined.Info,
                                contentDescription = AppTab.ABOUT.titleAr
                            )
                        },
                        label = { Text(AppTab.ABOUT.titleAr, maxLines = 1) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MasTealPrimary,
                            selectedTextColor = MasTealPrimary,
                            indicatorColor = MasTealLight
                        ),
                        modifier = Modifier.testTag("tab_about")
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                when (uiState.selectedTab) {
                    AppTab.CATALOG -> {
                        CatalogView(
                            uiState = uiState,
                            medicines = medicines,
                            onSearchQueryChanged = viewModel::onSearchQueryChanged,
                            onCategorySelected = viewModel::onCategorySelected,
                            onItemClick = viewModel::openMedicineDetails,
                            onAddToCart = viewModel::addToCart,
                            onCallClick = { viewModel.callPharmacy(context) },
                            onWhatsAppClick = { viewModel.openWhatsApp(context) },
                            onLocationClick = { viewModel.openLocationInMap(context) },
                            onPrescriptionClick = { viewModel.selectTab(AppTab.PRESCRIPTION) }
                        )
                    }

                    AppTab.PRESCRIPTION -> {
                        PrescriptionScreen(
                            orders = uiState.prescriptionOrders,
                            onSubmit = viewModel::submitPrescription,
                            onCallPharmacy = { viewModel.callPharmacy(context) }
                        )
                    }

                    AppTab.REMINDERS -> {
                        RemindersScreen(
                            reminders = uiState.reminders,
                            onToggleReminder = viewModel::toggleReminderTaken,
                            onDeleteReminder = viewModel::deleteReminder,
                            onAddReminder = viewModel::addReminder
                        )
                    }

                    AppTab.ABOUT -> {
                        AboutPharmacyScreen(
                            info = uiState.pharmacyInfo,
                            onCallClick = { viewModel.callPharmacy(context) },
                            onWhatsAppClick = { viewModel.openWhatsApp(context) },
                            onLocationClick = { viewModel.openLocationInMap(context) }
                        )
                    }
                }
            }
        }

        // Details Modal Dialog
        uiState.selectedMedicine?.let { med ->
            MedicineDetailsDialog(
                medicine = med,
                onDismiss = viewModel::closeMedicineDetails,
                onAddToCart = { viewModel.addToCart(med) }
            )
        }

        // Cart Bottom Sheet
        if (uiState.isCartOpen) {
            CartBottomSheet(
                cartItems = uiState.cartItems,
                onDismiss = { viewModel.setCartOpen(false) },
                onIncrease = { viewModel.addToCart(uiState.cartItems.first { it.medicine.id == it.medicine.id }.medicine) },
                onDecrease = viewModel::decreaseCartQuantity,
                onRemove = viewModel::removeFromCart,
                onCheckout = { name, phone, address, notes ->
                    viewModel.submitOrder(context, name, phone, address, notes)
                }
            )
        }
    }
}

@Composable
private fun CatalogView(
    uiState: com.example.viewmodel.PharmacyUiState,
    medicines: List<com.example.model.MedicineItem>,
    onSearchQueryChanged: (String) -> Unit,
    onCategorySelected: (MedicineCategory) -> Unit,
    onItemClick: (com.example.model.MedicineItem) -> Unit,
    onAddToCart: (com.example.model.MedicineItem) -> Unit,
    onCallClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onLocationClick: () -> Unit,
    onPrescriptionClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header Card (The Card from User Prompt & Uploaded Image)
        item {
            PharmacyHeaderCard(
                info = uiState.pharmacyInfo,
                onCallClick = onCallClick,
                onWhatsAppClick = onWhatsAppClick,
                onLocationClick = onLocationClick,
                onPrescriptionClick = onPrescriptionClick
            )
        }

        // Search Field
        item {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_medicine_input"),
                placeholder = { Text("ابحث عن دواء، فيتامين، أو مستلزم طبي...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "بحث",
                        tint = MasTealPrimary
                    )
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChanged("") }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "مسح")
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MasTealPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                singleLine = true
            )
        }

        // Category Filter Chips
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 2.dp)
            ) {
                items(MedicineCategory.values()) { cat ->
                    val isSelected = uiState.selectedCategory == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { onCategorySelected(cat) },
                        label = {
                            Text(
                                text = cat.titleAr,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MasTealPrimary,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }
        }

        // Section Title & Result count
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "قائمة الأدوية والمستلزمات",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MasTealDark
                    )
                )
                Text(
                    text = "${medicines.size} منتج",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        // Empty state
        if (medicines.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "لم يتم العثور على أدوية مطابقة للبحث",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "يمكنك طلب أي دواء غير مدرج مباشرة عبر إرسال الروشتة أو الاتصال بصيدلية ماس كير.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        } else {
            items(medicines, key = { it.id }) { medicine ->
                MedicineItemCard(
                    medicine = medicine,
                    onItemClick = { onItemClick(medicine) },
                    onAddToCart = { onAddToCart(medicine) }
                )
            }
        }
    }
}
