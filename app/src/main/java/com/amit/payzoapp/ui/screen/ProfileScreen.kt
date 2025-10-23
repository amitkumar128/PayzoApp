package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview
import com.amit.payzoapp.R

private val ScreenBg = Color(0xFFF6F8FA)
private val CardBg = Color(0xFFFFFFFF)
private val Accent = Color(0xFF5B4FFE)
private val Muted = Color(0xFF7B7F87)
private val SoftStroke = Color(0xFFE9E9EE)

@Composable
fun ProfileScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Top row with back button and title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button in white circle
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

            Spacer(modifier = Modifier.height(8.dp))

            // Profile header card
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
                        // Avatar circle (replace with Image when you have avatar drawable)
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "avatar",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Amit Kumar",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                // little badge icon
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "badge",
                                    tint = Color(0xFFFFD54F),
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Level 4",
                                fontSize = 12.sp,
                                color = Muted
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // progress bar row
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                LinearProgressIndicator(
                                    progress = 0.85f,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(6.dp)),
                                    color = Accent,
                                    trackColor = Color(0xFFF0EFFF)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "85%", fontSize = 12.sp, color = Muted)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats row: Transactions / Points / Rank
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text("1,208", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Accent)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Transactions", fontSize = 12.sp, color = Muted)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text("726", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Points", fontSize = 12.sp, color = Muted)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text("8", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Rank", fontSize = 12.sp, color = Muted)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Action buttons row (Edit Profile, Settings, Share) - equally sized
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { /* edit profile */ },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Edit Profile", fontSize = 12.sp, maxLines = 1)
                        }

                        OutlinedButton(
                            onClick = { /* settings */ },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings", modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Settings", fontSize = 12.sp, maxLines = 1)
                        }

                        OutlinedButton(
                            onClick = { /* share */ },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Share", fontSize = 12.sp, maxLines = 1)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Menu list contained in a flat card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // No shadow for this card
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ProfileMenuItem(drawableId = R.drawable.money, label = "All Transactions", onClick = { /* navigate */ })
                    ProfileMenuItem(drawableId = R.drawable.pending, label = "Pending Transactions", onClick = { /* navigate */ })
                    ProfileMenuItem(drawableId = R.drawable.refund, label = "Refund status", onClick = { /* navigate */ })
                    ProfileMenuItem(drawableId = R.drawable.raise_issue, label = "Raise an issue", onClick = { /* navigate */ })
                    ProfileMenuItem(drawableId = R.drawable.help, label = "Help and Support", isLastItem = true, onClick = { /* navigate */ })
                }
            }
        }
    }
}

/**
 * Profile menu item helper
 * - drawableId: optional drawable resource id to show at left
 * - label: text to show
 * - isLastItem: if true, don't draw divider below
 * - onClick: click handler
 */
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
                Icon(painter = painterResource(id = it), contentDescription = label, tint = Muted, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, modifier = Modifier.weight(1f), fontSize = 15.sp)
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "next", tint = Muted)
        }
        if (!isLastItem) {
            Divider(color = SoftStroke, thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfile() {
    ProfileScreen(navController = rememberNavController())
}
