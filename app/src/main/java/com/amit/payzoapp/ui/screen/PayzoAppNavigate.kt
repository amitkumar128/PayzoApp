package com.amit.payzoapp.ui.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amit.payzoapp.navigation.Screen
import com.amit.payzoapp.ui.theme.PayzoAppTheme

@Composable
fun PayzoAppNavigate() {

    val navController = rememberNavController()

    PayzoAppTheme {
        Surface {
            NavHost(navController = navController, startDestination = Screen.Splash.route) {
                // Splash -> goes to Login automatically
                composable(Screen.Splash.route) {
                    SplashScreen(navController = navController)
                }

                // Login receives the same navController to perform navigation to Home
                composable(Screen.Login.route) {
                    LoginScreen(navController = navController)
                }

                // Home should navigate to profile/offers using same navController
                composable(Screen.Home.route) {
                    HomeScreen(
                        onOpenProfile = { navController.navigate(Screen.Profile.route) },
                        navController = navController
                    )
                }

                composable(Screen.Offers.route) { OffersScreen(navController = navController) }
                composable(Screen.Balance.route) { BalanceScreen(navController = navController) }
                composable(Screen.Profile.route) { ProfileScreen(navController= navController) }
                composable(Screen.Rewards.route) { RewardsScreen(navController= navController) }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPayzo() {
    PayzoAppNavigate()
}