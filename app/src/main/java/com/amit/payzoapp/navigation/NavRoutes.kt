package com.amit.payzoapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")   // keep if you still use onboarding
    object Login : Screen("login")
    object Home : Screen("home")

    object Balance : Screen("balance")
    object Offers : Screen("offers")
    object Profile : Screen("profile")
    object Rewards : Screen("rewards")
}