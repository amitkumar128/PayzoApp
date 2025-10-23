package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.navigation.Screen

// Colors
private val ScreenBg = Color(0xFFF6F8FA)
private val CardBg = Color(0xFFFFFFFF)
private val Accent = Color(0xFF5B4FFE)
private val SoftPink = Color(0xFFFFE9F0)
private val SoftMint = Color(0xFFEFFBF5)
private val SoftLav = Color(0xFFF6F3FF)
private val PinkBtn = Color(0xFFFFCFE0)
private val TextGray = Color(0xFF6B6F76)

data class RewardItem(val id: String, val title: String, val subtitle: String)

@Composable
fun RewardsScreen(navController: NavController) {
    val rewards = remember {
        listOf(
            RewardItem("1", "Flat 50 off On Food Delivery", "On orders above ₹99 on Swiggy, Zomato"),
            RewardItem("2", "20% Cashback On Amazon", "Up to ₹150 on minimum order of ₹1000"),
            RewardItem("3", "5% Fuel Discount", "On selected fuel stations")
        )
    }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 🔙 Header (Back + Title)
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
                            navController.navigate(Screen.Home.route) {
                                launchSingleTop = true
                            }
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
                    text = "Rewards",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp).padding(bottom = 30.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // Cashback section
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CardBg)
                                .padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Cashbacks earned", color = TextGray, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("₹507", fontSize = 34.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("+ ₹88 this month", fontSize = 12.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { /* view cashback history */ },
                                colors = ButtonDefaults.buttonColors(containerColor = SoftPink),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth(0.9f)
                            ) {
                                Text("View your cashback history", color = Color(0xFF9B3B63))
                            }
                        }
                    }
                }

                // Scratchcards section
                item {
                    Column {
                        Text("Scratchcards won", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(width = 100.dp, height = 72.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SoftLav)
                            )
                            Box(
                                modifier = Modifier
                                    .size(width = 100.dp, height = 72.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SoftPink)
                            )
                            Box(
                                modifier = Modifier
                                    .size(width = 100.dp, height = 72.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SoftMint)
                            )
                        }
                    }
                }

                // Collect Rewards section
                item {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Collect Rewards", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(rewards) { r ->
                    RewardRow(item = r, onCollect = { /* collect action */ })
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp)) // Add some space at the very bottom
                }
            }
        }
    }
}

@Composable
private fun RewardRow(item: RewardItem, onCollect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 84.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Illustration placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SoftMint),
                contentAlignment = Alignment.Center
            ) {
                Text("🎁", fontSize = 20.sp, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    item.subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onCollect,
                colors = ButtonDefaults.buttonColors(containerColor = PinkBtn),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(text = "Collect Now", color = Color(0xFF9B3B63))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRewards() {
    RewardsScreen(navController = rememberNavController())
}
