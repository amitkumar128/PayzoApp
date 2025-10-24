package com.amit.payzoapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.R
import com.amit.payzoapp.navigation.Screen
import com.amit.payzoapp.ui.viewmodel.RewardViewModel
import kotlinx.coroutines.launch

// Colors
private val ScreenBg = Color(0xFFF6F8FA)
private val CardBg = Color(0xFFFFFFFF)
private val Accent = Color(0xFF5B4FFE)
private val SoftPink = Color(0xFFFFE9F0)
private val SoftMint = Color(0xFFEFFBF5)
private val SoftLav = Color(0xFFF6F3FF)
private val PinkBtn = Color(0xFFFFCFE0)
private val TextGray = Color(0xFF6B6F76)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(
    navController: NavController,
    rewardViewModel: RewardViewModel = viewModel()
) {
    val cashback by remember { derivedStateOf { rewardViewModel.cashback } }
    val scratchcardsCount by remember { derivedStateOf { rewardViewModel.scratchcardsCount } }
    val rewards by remember { derivedStateOf { rewardViewModel.rewards } }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Custom saver for the SnapshotStateMap
    val expandedMapSaver = Saver<SnapshotStateMap<String, Boolean>, Any>(
        save = { it.toList() },
        restore = { mutableStateMapOf<String, Boolean>().apply { putAll(it as List<Pair<String, Boolean>>) } }
    )

    // track expanded cards (id -> expanded)
    val expandedMap = rememberSaveable(saver = expandedMapSaver) { mutableStateMapOf() }

    Scaffold(
        containerColor = ScreenBg,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ScreenBg
                ),
                title = {
                    Text(
                        "Rewards",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                navigationIcon = {
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
                            .padding(start = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Accent)
                    }
                }

            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // Header card: cashback + small CTA
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(listOf(Accent, Color(0xFF8E7BFF)))
                            )
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                "Cashbacks earned",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                cashback,
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        "+ ₹88 this month",
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    // subtle mini progress bar to show "earning progress"
                                    LinearProgressIndicator(
                                        progress = 0.42f,
                                        modifier = Modifier
                                            .width(160.dp)
                                            .height(6.dp)
                                            .clip(RoundedCornerShape(6.dp)),
                                        color = Color.White,
                                        trackColor = Color.White.copy(alpha = 0.12f)
                                    )
                                }

                                Button(
                                    onClick = { /* view history */ },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("View history", color = Accent)
                                }
                            }
                        }
                    }
                }
            }

            // Scratchcards: horizontal scroll chips
            item {
                Column {
                    Text("Scratchcards won", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Using items for a lazy row can be more efficient if the list grows
                        item { ScratchCardChip(label = "🎉 x$scratchcardsCount") }
                        item { ScratchCardChip(label = "Tap to reveal") }
                        item { ScratchCardChip(label = "Extra reward") }
                        // Alternatively, if you have a list:
                        // items(listOf("🎉 x$scratchcardsCount", "Tap to reveal", "Extra reward")) { label -> ScratchCardChip(label) }
                    }
                }
            }

            // Search + filters row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = "",
                        placeholder = {
                            Text(
                                "Search rewards",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onValueChange = { /* wire to search state */ },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Filter chip (UI only)
                    AssistChip(
                        onClick = { /* filter menu */ },
                        label = { Text("All") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.filter_list),
                                contentDescription = "filter",
                                tint = Accent
                            )
                        }
                    )
                }
            }

            // Section title
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Text("Collect Rewards", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // rewards list
            items(rewards, key = { it.id }) { r ->
                // card is clickable to expand for more info
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expandedMap[r.id] = !(expandedMap[r.id] ?: false)
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // illustration square
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SoftMint),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🎁", fontSize = 20.sp)
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    r.title,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    r.subtitle,
                                    fontSize = 12.sp,
                                    color = TextGray,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            // collected badge or collect button
                            AnimatedVisibility(
                                visible = r.collected,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.CheckCircle,
                                        contentDescription = "collected",
                                        tint = Color(0xFF2EB886)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Collected", fontSize = 12.sp, color = Color(0xFF2EB886))
                                }
                            }

                            if (!r.collected) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        // collect reward
                                        rewardViewModel.collectReward(r.id)
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Reward collected: ${r.title}")
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = PinkBtn),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.height(38.dp)
                                ) {
                                    Text("Collect", color = Color(0xFF9B3B63))
                                }
                            }
                        }

                        // expandable area with details & CTA
                        val isExpanded = expandedMap[r.id] ?: false
                        AnimatedVisibility(
                            visible = isExpanded,
                            enter = expandVertically() + fadeIn(),
                            exit = fadeOut()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            ) {
                                Text(
                                    "Offer details and terms go here. Tap again to collapse.",
                                    fontSize = 12.sp,
                                    color = TextGray
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    TextButton(onClick = { /* apply code / view merchant */ }) {
                                        Text(
                                            "Use Offer"
                                        )
                                    }
                                    TextButton(onClick = { /* share */ }) { Text("Share") }
                                }
                            }
                        }
                    }
                }
            }

            // bottom spacing
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun ScratchCardChip(label: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
        color = CardBg,
        modifier = Modifier
            .widthIn(min = 100.dp)
            .height(72.dp)
            .padding(horizontal = 4.dp) // Add padding to prevent content from touching edges
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                label,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRewards() {
    RewardsScreen(navController = rememberNavController())
}
