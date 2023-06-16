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

    // scan screen
    object ScanSelection : Screen("scan_selection")
    object CalScan : Screen("cal_scan")
    object ResultScan : Screen("result_scan")
    object Saved : Screen("saved_scan")

    object WebView : Screen("saved_scan/{texturl}/{imgurl}") {
        fun createRoute(texturl: String, imgurl: String) = "saved_scan/$texturl/$imgurl"
    }
}