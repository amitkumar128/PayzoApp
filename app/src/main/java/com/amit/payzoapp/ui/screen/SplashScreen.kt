package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amit.payzoapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, delayMillis: Long = 1500L) {
    // Auto navigate to Login after delay
    LaunchedEffect(Unit) {
        delay(delayMillis)
        navController.navigate(Screen.Login.route) {
            // Remove splash from backstack so back won't return to it
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    // Simple splash UI - replace with your logo/image
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF5B4FFE), shape = RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("⚡", fontSize = 36.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("INSTANT PAY", fontSize = 18.sp, color = Color(0xFF5B4FFE))
        }
    }
}