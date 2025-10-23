package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amit.payzoapp.R
import com.amit.payzoapp.ui.components.HomeTopBarSection

// Colors (match screenshot look)
private val ScreenBg = Color(0xFFF6F8FA)
private val Accent = Color(0xFF5B4FFE)
private val PastelBlue = Color(0xFFDCEEFF)
private val PastelLavender = Color(0xFFF3EEFF)
private val PastelPink = Color(0xFFFFEBF0)
private val PastelGreen = Color(0xFFDFF7E6)
private val SoftPinkIcon = Color(0xFFFFEAF0)

/**
 * Home screen: top bar moved to HomeTopBarSection. Remaining content below.
 */
@Composable
fun HomeScreen(onOpenProfile: () -> Unit, navController: NavController) {
    // data for UI
    val moneyChips = listOf(
        Triple("Scan QR Code", R.drawable.qr_code, PastelBlue),
        Triple("Send to Contact", R.drawable.person, PastelLavender),
        Triple("Send To Bank", R.drawable.account_balance, PastelGreen),
        Triple("Self Transfer", R.drawable.sync, PastelPink)
    )

    val rechargeChips = listOf(
        Triple("Mobile Recharge", R.drawable.mobile, PastelLavender),
        Triple("Electricity Bill", R.drawable.lightbulb, PastelPink),
        Triple("DTH Recharge", R.drawable.outline_satellite_alt_24, PastelBlue),
        Triple("Postpaid bill", R.drawable.receipt, PastelGreen)
    )

    val ticketItems = listOf(
        Pair("Movies", R.drawable.movie),
        Pair("Trains", R.drawable.train),
        Pair("Bus", R.drawable.bus),
        Pair("Flights", R.drawable.flight),
        Pair("Car", R.drawable.car)
    )

    val services = listOf(
        Pair("Invest", R.drawable.chart),
        Pair("Loans", R.drawable.currency_rupee),
        Pair("Insurance", R.drawable.favorite),
        Pair("Fastag", R.drawable.payments)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(ScreenBg)
            .padding(bottom = 40.dp)
    ) {
        // top bar component
        HomeTopBarSection(navController = navController, onOpenProfile = onOpenProfile)

        Spacer(modifier = Modifier.height(14.dp))

        // Money Transfer section
        MoneyTransferSection(chips = moneyChips)

        Spacer(modifier = Modifier.height(14.dp))

        // Recharge section
        RechargeSection(chips = rechargeChips)

        Spacer(modifier = Modifier.height(16.dp))

        // Ticket Booking
        TicketBookingSection(items = ticketItems)

        Spacer(modifier = Modifier.height(16.dp))

        // More Services
        MoreServicesSection(items = services)

        Spacer(modifier = Modifier.height(30.dp))
    }
}

/* ---------------- Sections / Components ---------------- */

@Composable
private fun MoneyTransferSection(chips: List<Triple<String, Int, Color>>) {
    Column(modifier = Modifier.padding(horizontal = 14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Money Transfer", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = { /* more */ }) { Text("More") }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MoneyTransferChip(chips[0].first, chips[0].second, chips[0].third, modifier = Modifier.weight(1f))
                MoneyTransferChip(chips[1].first, chips[1].second, chips[1].third, modifier = Modifier.weight(1f))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MoneyTransferChip(chips[2].first, chips[2].second, chips[2].third, modifier = Modifier.weight(1f))
                MoneyTransferChip(chips[3].first, chips[3].second, chips[3].third, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun RechargeSection(chips: List<Triple<String, Int, Color>>) {
    Column(modifier = Modifier.padding(horizontal = 14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Recharge & Bill Payments", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = { /* more */ }) { Text("More") }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MoneyTransferChip(chips[0].first, chips[0].second, chips[0].third, modifier = Modifier.weight(1f))
                MoneyTransferChip(chips[1].first, chips[1].second, chips[1].third, modifier = Modifier.weight(1f))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MoneyTransferChip(chips[2].first, chips[2].second, chips[2].third, modifier = Modifier.weight(1f))
                MoneyTransferChip(chips[3].first, chips[3].second, chips[3].third, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun TicketBookingSection(items: List<Pair<String, Int>>) {
    Column(modifier = Modifier.padding(horizontal = 14.dp)) {
        Text("Ticket Booking", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items) { pair ->
                TicketIcon(text = pair.first, drawable = pair.second)
            }
        }
    }
}

@Composable
private fun MoreServicesSection(items: List<Pair<String, Int>>) {
    Column(modifier = Modifier.padding(horizontal = 14.dp)) {
        Text("More Services", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items.forEach { (label, drawable) ->
                SmallServiceSquare(label = label, drawable = drawable)
            }
        }
    }
}

/* ---------------- Small UI pieces (chips/icons) ---------------- */

@Composable
private fun MoneyTransferChip(label: String, drawableRes: Int, bg: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(64.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(bg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = drawableRes),
                    contentDescription = label,
                    tint = Accent,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(label, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun TicketIcon(text: String, drawable: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(72.dp)) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = SoftPinkIcon,
            modifier = Modifier.size(64.dp),
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = text,
                    tint = Color(0xFFEF6C8E),
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun SmallServiceSquare(label: String, drawable: Int) {
    Surface(
        modifier = Modifier
            .width(84.dp)
            .height(64.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = label,
                tint = Accent,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(label, fontSize = 12.sp)
        }
    }
}
