package com.example.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.PharmacyInfo
import com.example.ui.theme.MasGoldAccent
import com.example.ui.theme.MasTealAccent
import com.example.ui.theme.MasTealDark
import com.example.ui.theme.MasTealLight
import com.example.ui.theme.MasTealPrimary

@Composable
fun PharmacyHeaderCard(
    info: PharmacyInfo,
    onCallClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onLocationClick: () -> Unit,
    onPrescriptionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("pharmacy_header_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Main Branding Section (Matching the user's business card)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo Emblem Container
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    MasTealPrimary.copy(alpha = 0.12f),
                                    MasTealAccent.copy(alpha = 0.06f)
                                )
                            )
                        )
                        .border(1.5.dp, MasTealPrimary.copy(alpha = 0.3f), RoundedCornerShape(18.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_mas_care_logo),
                        contentDescription = "شعار صيدلية ماس كير",
                        modifier = Modifier
                            .size(62.dp)
                            .clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Brand Names & Slogan
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = info.nameAr,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = MasTealDark
                        )
                    )
                    Text(
                        text = info.nameEn,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.2.sp,
                            color = MasTealAccent
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2E7D32))
                        )
                        Text(
                            text = "${info.sloganAr} • ${info.sloganEn}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Three Core Pillars (دواء - أمان - عناية) matching the uploaded business card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MasTealLight.copy(alpha = 0.45f))
                    .padding(vertical = 10.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PillarBadge(
                    icon = Icons.Default.Medication,
                    title = "دواء",
                    subtitle = "أصلي ومضمون"
                )
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .width(1.dp)
                        .background(MasTealPrimary.copy(alpha = 0.2f))
                )
                PillarBadge(
                    icon = Icons.Default.Security,
                    title = "أمان",
                    subtitle = "أعلى معايير الحفظ"
                )
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .width(1.dp)
                        .background(MasTealPrimary.copy(alpha = 0.2f))
                )
                PillarBadge(
                    icon = Icons.Default.Favorite,
                    title = "عناية",
                    subtitle = "رعاية تليق بك"
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Address & Phone Box (Specified directly in User Prompt)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    // Address
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "العنوان",
                            tint = MasTealPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "العنوان:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MasTealPrimary
                                )
                            )
                            Text(
                                text = info.address,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    )

                    // Phone & Working Hours
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onCallClick() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "الهاتف",
                                tint = MasTealPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "تلفون: ${info.phone}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MasTealDark
                                )
                            )
                        }

                        // Open 24/7 indicator badge
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MasTealPrimary.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "24/7 مفتوح",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MasTealPrimary
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onCallClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("call_pharmacy_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MasTealPrimary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("اتصال", fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = onWhatsAppClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("whatsapp_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1B5E20))
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF2E7D32)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("واتساب", fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = onLocationClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("maps_location_button"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("الخريطة", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun PillarBadge(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, MasTealPrimary.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MasTealPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MasTealDark
            )
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            textAlign = TextAlign.Center
        )
    }
}
