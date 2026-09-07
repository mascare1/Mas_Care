package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PrescriptionOrder
import com.example.ui.theme.MasTealAccent
import com.example.ui.theme.MasTealDark
import com.example.ui.theme.MasTealLight
import com.example.ui.theme.MasTealPrimary

@Composable
fun PrescriptionScreen(
    orders: List<PrescriptionOrder>,
    onSubmit: (context: Context, name: String, phone: String, address: String, notes: String, isUrgent: Boolean, hasPhoto: Boolean) -> Unit,
    onCallPharmacy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var patientName by remember { mutableStateOf("") }
    var patientPhone by remember { mutableStateOf("") }
    var patientAddress by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf(false) }
    var hasSimulatedPhoto by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("prescription_screen")
    ) {
        // Banner card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MasTealDark),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "خدمة صرف الروشتات الطبية",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "صيدلية ماس كير - صنعاء",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MasTealLight
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "أرفق صورة الروشتة أو اكتب أصناف الأدوية المطلوبة، وسيقوم الصيدلاني بمراجعتها وتجهيزها للتوصيل السريع إلى منزلك.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White.copy(alpha = 0.9f),
                        lineHeight = 20.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "صورة الوصفة الطبية (الروشتة)",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MasTealDark
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (hasSimulatedPhoto) MasTealLight else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        .border(
                            width = 1.5.dp,
                            color = if (hasSimulatedPhoto) MasTealPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { hasSimulatedPhoto = !hasSimulatedPhoto }
                        .testTag("upload_prescription_photo_box"),
                    contentAlignment = Alignment.Center
                ) {
                    if (hasSimulatedPhoto) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "تم إرفاق صورة الروشتة بنجاح ✓",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E7D32)
                                )
                            )
                            Text(
                                text = "انقر لتغيير أو إزالة الصورة",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.AddAPhoto,
                                contentDescription = null,
                                tint = MasTealPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "انقر لالتقاط أو إرفاق صورة الروشتة",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MasTealPrimary
                                )
                            )
                            Text(
                                text = "يدعم الكاميرا والاستديو",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Form Fields
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "بيانات المريض والتوصيل",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MasTealDark
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = patientName,
                    onValueChange = { patientName = it },
                    label = { Text("اسم المريض أو طالب الدواء") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("prescription_input_name"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = patientPhone,
                    onValueChange = { patientPhone = it },
                    label = { Text("رقم الهاتف (للتواصل وتأكيد الأصناف)") },
                    placeholder = { Text("مثال: 785665658 أو 77xxxxxxx") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("prescription_input_phone"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = patientAddress,
                    onValueChange = { patientAddress = it },
                    label = { Text("عنوان التوصيل بصنعاء بالتفصيل") },
                    placeholder = { Text("شارع عمان / الجزائر / حدة / الدائري...") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    singleLine = false,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("prescription_input_address"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("أسماء الأدوية أو ملاحظات إضافية") },
                    placeholder = { Text("اكتب أسماء الأدوية أو أي استفسار لصيدلاني ماس كير...") },
                    singleLine = false,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("prescription_input_notes"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Urgent switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (isUrgent) Color(0xFFFFEBEE) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FlashOn,
                            contentDescription = null,
                            tint = if (isUrgent) Color(0xFFC62828) else MasTealPrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "حالة عاجلة / طارئة",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isUrgent) Color(0xFFC62828) else MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Text(
                                text = "تجهيز فوري وتوصيل بأولوية قصوى",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }

                    Switch(
                        checked = isUrgent,
                        onCheckedChange = { isUrgent = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFFC62828)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSubmit(
                            context,
                            patientName.ifBlank { "مريض صيدلية ماس كير" },
                            patientPhone.ifBlank { "785665658" },
                            patientAddress.ifBlank { "صنعاء" },
                            notes,
                            isUrgent,
                            hasSimulatedPhoto
                        )
                        patientName = ""
                        patientPhone = ""
                        patientAddress = ""
                        notes = ""
                        hasSimulatedPhoto = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("submit_prescription_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MasTealPrimary)
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "إرسال الروشتة لصيدلية ماس كير",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        // Previous orders section if any
        if (orders.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "الروشتات المرسلة مؤخراً (${orders.size}):",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MasTealDark
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            orders.forEach { order ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = order.patientName,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "الهاتف: ${order.phone} • ${order.address}",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFE8F5E9)
                        ) {
                            Text(
                                text = order.status,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E7D32)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
