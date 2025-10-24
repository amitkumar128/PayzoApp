package com.amit.payzoapp.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.amit.payzoapp.R
import com.amit.payzoapp.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, delayMillis: Long = 1500L) {
    val scale = remember { Animatable(0.5f) }
    val coroutineScope = rememberCoroutineScope()

    val navigateToLogin = {
        navController.navigate(Screen.Login.route) {
            // Remove splash from backstack so back won't return to it
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    // Animate the logo on first composition
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800
            )
        )
        delay(delayMillis)
        navigateToLogin()
    }

    // Simple splash UI - replace with your logo/image
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8FA)),
        contentAlignment = Alignment.Center // This centers the Column in the Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // no ripple effect
            ) {
                coroutineScope.launch { navigateToLogin() }
            }
        ) {
            Box(
                modifier = Modifier
                    .scale(scale.value)
                    .size(120.dp)
                    .background(Color(0xFF5B4FFE), shape = RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center // This centers the Text in the Box
            ) {
                AsyncImage(
                    model = R.mipmap.ic_launcher,
                    contentDescription = "App Logo",
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("PayZo", fontSize = 18.sp, color = Color(0xFF5B4FFE))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplash() {
    SplashScreen(navController = rememberNavController())
}