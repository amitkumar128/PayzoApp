package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.amit.payzoapp.R
import com.amit.payzoapp.navigation.Screen
import com.amit.payzoapp.ui.viewmodel.ProfileViewModel

private val ScreenBg = Color(0xFFF6F8FA)
private val CardBg = Color(0xFFFFFFFF)
private val Accent = Color(0xFF5B4FFE)
private val Muted = Color(0xFF7B7F87)
private val SoftStroke = Color(0xFFE9E9EE)

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val name by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.name } }
    val level by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.levelLabel } }
    val progress by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.progress } }
    val transactions by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.transactionsCount } }
    val points by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.pointsCount } }
    val rank by androidx.compose.runtime.remember { androidx.compose.runtime.derivedStateOf { profileViewModel.rank } }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val popped = navController.popBackStack()
                        if (!popped) {
                            navController.navigate(Screen.Home.route) { launchSingleTop = true }
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Accent
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            // ---- Profile Header ----
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardBg)
                        .padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = R.mipmap.ic_launcher,
                                contentDescription = "avatar",
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "badge",
                                    tint = Color(0xFFFFD54F),
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(level, fontSize = 12.sp, color = Muted)
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                LinearProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(6.dp)),
                                    // In Material 3, the 'color' parameter is renamed to 'progressColor'.
                                    color = Accent,
                                    trackColor = Color(0xFFF0EFFF)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "${(progress * 100).toInt()}%", fontSize = 12.sp, color = Muted
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ProfileStat("Transactions", transactions, Accent, R.drawable.money)
                        }
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ProfileStat(
                                "Points",
                                points,
                                Color.Black,
                                R.drawable.currency_rupee_circle
                            )
                        }
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ProfileStat("Rank", rank, Color.Black, R.drawable.chart)
                        }
//
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // ---- Action Buttons ----
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProfileButton(Icons.Default.Edit, "Edit Profile") { }
                        ProfileButton(Icons.Default.Settings, "Settings") { }
                        ProfileButton(Icons.Default.Share, "Share") { }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ---- Menu Section ----
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ProfileMenuItem(R.drawable.money, "All Transactions") { }
                    ProfileMenuItem(R.drawable.pending, "Pending Transactions") { }
                    ProfileMenuItem(R.drawable.refund, "Refund status") { }
                    ProfileMenuItem(R.drawable.raise_issue, "Raise an issue") { }
                    ProfileMenuItem(R.drawable.help, "Help and Support", isLastItem = true) { }
                }
            }
        }
    }
}

@Composable
private fun ProfileStat(label: String, value: String, color: Color, iconResId: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(value, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = color)
            Text(label, fontSize = 12.sp, color = Muted)
        }
    }
}

@Composable
private fun RowScope.ProfileButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp), // Modifier.weight(1f) needs to be applied in a RowScope
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 12.sp, maxLines = 1)
    }
}

@Composable
private fun ProfileMenuItem(
    drawableId: Int? = null,
    label: String,
    isLastItem: Boolean = false,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            drawableId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = label,
                    tint = Muted,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, modifier = Modifier.weight(1f), fontSize = 15.sp)
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "next",
                tint = Muted
            )
        }
        if (!isLastItem) Divider(color = SoftStroke, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfile() {
    ProfileScreen(navController = rememberNavController())
}
