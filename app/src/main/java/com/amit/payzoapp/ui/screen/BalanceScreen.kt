package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.R
import com.amit.payzoapp.data.balance.request.Balance
import com.amit.payzoapp.ui.viewmodel.BalanceViewModel

private val ScreenBg = Color(0xFFF6F8FA)
private val Accent = Color(0xFF5B4FFE)
private val Positive = Color(0xFF2EB886)
private val Negative = Color(0xFFEF6C6C)

@Composable
fun BalanceScreen(
    navController: NavController,
    // default to Hilt-provided viewModel. If you don't use Hilt, pass your ViewModel instance explicitly.
    balanceViewModel: BalanceViewModel = hiltViewModel()
) {
    // Collect StateFlows from the ViewModel as Compose State.
    // using collectAsStateWithLifecycle (safer for lifecycle).
    val balance by balanceViewModel.balance.collectAsStateWithLifecycle()
    val cashback by balanceViewModel.cashback.collectAsStateWithLifecycle()
    val points by balanceViewModel.points.collectAsStateWithLifecycle()
    val balances by balanceViewModel.transactions.collectAsStateWithLifecycle(initialValue = emptyList())

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ---- Top Bar ----
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
                            navController.navigate(com.amit.payzoapp.navigation.Screen.Home.route) {
                                launchSingleTop = true
                            }
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Accent)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Balance",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.weight(1f)
                )

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

            // ---- Wallet Card ----
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
                            balance,
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
                                Text(
                                    cashback,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    "Points",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                                Text(points, color = Color.White, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ---- Quick Actions ----
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionSmall(
                    "Send",
                    ImageVector.vectorResource(R.drawable.send_money),
                    Color(0xFFEEE6FF)
                )
                QuickActionSmall(
                    "Receive",
                    ImageVector.vectorResource(R.drawable.money),
                    Color(0xFFE6FFF1)
                )
                QuickActionSmall(
                    "UPI Collect",
                    ImageVector.vectorResource(R.drawable.upi_pay),
                    Color(0xFFFFF0F3)
                )
                QuickActionSmall(
                    "History",
                    ImageVector.vectorResource(R.drawable.history),
                    Color(0xFFF3F6FF)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ---- Recent Transactions ----
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recent Transactions", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { /* view all */ }) { Text("View all") }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 60.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(balances) { balance -> TransactionRow(balance) }
            }
        }
    }
}

@Composable
private fun RowScope.QuickActionSmall(text: String, icon: ImageVector, bg: Color) {
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
                Icon(icon, contentDescription = text, tint = Accent)
            }
            Spacer(modifier = Modifier.height(0.dp))
            Text(text, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun TransactionRow(balance: Balance) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (balance.positive) Color(0xFFE6FFF1) else Color(0xFFFFF0F0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (balance.positive) ImageVector.vectorResource(id = R.drawable.arrow_downward)
                    else ImageVector.vectorResource(id = R.drawable.arrow_upward),
                    contentDescription = null,
                    tint = if (balance.positive) Positive else Negative
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    balance.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(balance.subtitle, fontSize = 12.sp, color = Color.Gray)
            }

            Text(
                balance.amount,
                fontWeight = FontWeight.SemiBold,
                color = if (balance.positive) Positive else Negative
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBalance() {
    // Preview can't resolve HiltViewModel; use a plain viewModel for preview.
    val fakeVm: BalanceViewModel =
        viewModel() // preview only; ensure a no-arg constructor or provide a test VM when previewing
    BalanceScreen(navController = rememberNavController(), balanceViewModel = fakeVm)
}
