package com.example.nutriku.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object OnBoarding : Screen("onboarding")
    object Profile : Screen("profile")
    object Scan : Screen("scan")
    object Explore : Screen("explore")
    object History : Screen("history")
}