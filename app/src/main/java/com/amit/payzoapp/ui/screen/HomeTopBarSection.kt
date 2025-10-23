package com.amit.payzoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amit.payzoapp.navigation.Screen

private val Accent = Color(0xFF5B4FFE)
private val MutedText = Color(0xFF9AA0AB)

/**
 * Top header for Home screen.
 * Contains avatar, search pill, notification bell and the pill-style tabs.
 *
 * When avatar is clicked it will call onOpenProfile() and navigate to Screen.Profile
 * using the supplied navController.
 */
@Composable
fun HomeTopBarSection(
    navController: NavController,
    onOpenProfile: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Balance", "Offers", "Rewards")

    Column(modifier = modifier.fillMaxWidth()) {
        // Profile / Search / Bell row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // avatar (replace with Image if needed)
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable {
                        // call optional callback
                        onOpenProfile()
                        // navigate to Profile screen
                        navController.navigate(Screen.Profile.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("E", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Search pill
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search Users, ID's etc", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "search") },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = 44.dp),
                shape = RoundedCornerShape(22.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(onClick = { /* notifications */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "notifications")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tabs pill container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabs.forEachIndexed { index, title ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    selectedTab = index
                                    when (title) {
                                        "Balance" -> navController.navigate(Screen.Balance.route)
                                        "Offers" -> navController.navigate(Screen.Offers.route)
                                        "Rewards" -> navController.navigate(Screen.Rewards.route)
                                        else -> { /* Home */ }
                                    }
                                }
                                .padding(vertical = 6.dp)
                        ) {
                            Text(
                                text = title,
                                color = if (selectedTab == index) Accent else Color.Gray,
                                fontWeight = if (selectedTab == index) FontWeight.SemiBold else FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            if (selectedTab == index) {
                                Box(
                                    modifier = Modifier
                                        .height(4.dp)
                                        .width(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Accent)
                                )
                            } else {
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
