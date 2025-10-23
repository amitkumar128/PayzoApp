package com.amit.payzoapp.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.R
import com.amit.payzoapp.navigation.Screen

// Pastel palette
private val Pastel1 = Color(0xFFFFF5F7)
private val Pastel2 = Color(0xFFF6FFF6)
private val Pastel3 = Color(0xFFFBF7FF)
private val Pastel4 = Color(0xFFF8FBFF)
private val ScreenBg = Color(0xFFF6F8FA)
private val Accent = Color(0xFF5B4FFE)

data class OfferUi(
    val id: String,
    val title: String,
    val subtitle: String,
    val code: String?,
    @DrawableRes val icon: Int
)

@Composable
fun OffersScreen(navController: NavController) {
    val offers = remember {
        listOf(
            OfferUi(
                id = "dth",
                title = "DTH Recharge Offer",
                subtitle = "Get 20% instant cashback up to ₹50 on your first DTH recharge. T&C apply",
                code = "FIRSTDHT20",
                icon = R.drawable.outline_satellite_alt_24
            ),
            OfferUi(
                id = "flipkart",
                title = "Flipkart Shopping Offer",
                subtitle = "Shop on Flipkart using our payment system to get up to 50% cashback. T&C apply",
                code = null,
                icon = R.drawable.shopping_bag
            ),
            OfferUi(
                id = "money_transfer",
                title = "Money Transfer Offer",
                subtitle = "Get a scratch card with assured cashback and coupons on transfers of ₹500 or more. T&C apply",
                code = null,
                icon = R.drawable.send_money
            ),
            OfferUi(
                id = "flights",
                title = "Rs 50 Off on Flights",
                subtitle = "Get instant discount on flat ₹50 on Flight ticket booking. T&C apply",
                code = null,
                icon = R.drawable.flight
            )
        )
    }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBg) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 🌟 Header with Back Icon and Title
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
                    text = "Offers",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 60.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(offers) { index, offer ->
                    OfferCard(
                        offer = offer,
                        bg = when (index % 4) {
                            0 -> Pastel1
                            1 -> Pastel2
                            2 -> Pastel3
                            else -> Pastel4
                        },
                        onClick = {
                            // TODO: navigate to offer details or open webview
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun OfferCard(offer: OfferUi, bg: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 92.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon area
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = offer.icon),
                    contentDescription = offer.title,
                    tint = Accent,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = offer.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = offer.subtitle,
                    fontSize = 13.sp,
                    color = Color(0xFF6B6F76),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Code and CTA
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!offer.code.isNullOrEmpty()) {
                        Surface(
                            tonalElevation = 0.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = offer.code,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Accent
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = { /* TODO: open offer */ },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Go to offer page",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOffers() {
    OffersScreen(navController = rememberNavController())
}
