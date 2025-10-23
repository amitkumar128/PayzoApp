package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amit.payzoapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

private val ScreenBg = Color(0xFFF6F8FA)
private val Accent = Color(0xFF5B4FFE)
private val Positive = Color(0xFF2EB886)
private val Negative = Color(0xFFEF6C6C)

data class Tx(
    val id: String,
    val title: String,
    val subtitle: String,
    val amount: String,
    val positive: Boolean
)

@Composable
fun BalanceScreen(navController: NavController) {
    // dummy transactions
    val txs = remember {
        listOf(
            Tx("1", "Salary Credited", "Company - Sept", "+₹75,000", true),
            Tx("2", "Electricity Bill", "TNEB - Sept", "-₹3,420", false),
            Tx("3", "Mobile Recharge", "Airtel", "-₹399", false),
            Tx("4", "Money Received", "From Rahul", "+₹2,500", true),
            Tx("5", "Flight Booking", "IndiGo", "-₹4,999", false)
        )
    }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Top Bar with Back Icon + Title + Add Money
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Icon Button - robust behavior:
                IconButton(
                    onClick = {
                        // Try to pop back — if that fails, navigate to Home explicitly
                        val popped = navController.popBackStack()
                        if (!popped) {
                            navController.navigate(com.amit.payzoapp.navigation.Screen.Home.route) {
                                launchSingleTop = true
                                // don't clear whole backstack here so natural back works
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
                    text = "Balance",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.weight(1f)
                )

                // Add Money button as small pill
                Button(
                    onClick = { /* add money */ },
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Add Money")
                }
            }

            // Wallet card with gradient and balance + mini stats
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(
                            Brush.horizontalGradient(listOf(Accent, Color(0xFF8E7BFF)))
                        )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Wallet Balance", color = Color.White.copy(alpha = 0.9f))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "₹ 28,540",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    "Cashback",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                                Text("₹507", color = Color.White, fontWeight = FontWeight.SemiBold)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    "Points",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                                Text("726 pts", color = Color.White, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Quick actions row (Send, Receive, UPI, History)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionSmall(
                    "Send",
                    ImageVector.vectorResource(id = R.drawable.send_money),
                    Color(0xFFEEE6FF)
                )
                QuickActionSmall(
                    "Receive", ImageVector.vectorResource(id = R.drawable.money), Color(0xFFE6FFF1)
                )
                QuickActionSmall(
                    "UPI Collect",
                    ImageVector.vectorResource(id = R.drawable.upi_pay),
                    Color(0xFFFFF0F3)
                )
                QuickActionSmall(
                    "History",
                    ImageVector.vectorResource(id = R.drawable.history),
                    Color(0xFFF3F6FF)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Recent transactions header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recent Transactions", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { /* view all */ }) {
                    Text("View all")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Transaction list
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp), // Existing horizontal padding
                contentPadding = PaddingValues(bottom = 60.dp), // Add padding to the bottom of the content
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(txs) { tx ->
                    TransactionRow(tx = tx)
                }
            }
        }
    }
}

@Composable
private fun RowScope.QuickActionSmall(
    text: String, icon: ImageVector, bg: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .weight(1f)
            .height(76.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(bg),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = text, tint = Color(0xFF5B4FFE))
            }
            Spacer(modifier = Modifier.height(0.dp))
            Text(text, fontSize = 13.sp, fontWeight = FontWeight.Medium, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
private fun TransactionRow(tx: Tx) {
    Card(
        shape = RoundedCornerShape(12.dp), modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // icon bubble
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (tx.positive) Color(0xFFE6FFF1) else Color(0xFFFFF0F0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (tx.positive) ImageVector.vectorResource(id = R.drawable.arrow_downward)
                    else ImageVector.vectorResource(id = R.drawable.arrow_upward),
                    contentDescription = null,
                    tint = if (tx.positive) Positive else Negative
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    tx.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(tx.subtitle, fontSize = 12.sp, color = Color.Gray)
            }

            Text(
                tx.amount,
                fontWeight = FontWeight.SemiBold,
                color = if (tx.positive) Positive else Negative
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBalance() {
    // For preview we provide a dummy navController
    BalanceScreen(navController = rememberNavController())
}
