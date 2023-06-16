package com.example.nutriku

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myjetpackapplication.R
import com.example.nutriku.component.BottomBar
import com.example.nutriku.component.HistoryScreen
import com.example.nutriku.navigation.NavigationItem
import com.example.nutriku.navigation.Screen
import com.example.nutriku.screen.ArticleScreen
import com.example.nutriku.screen.HomeScreen
import com.example.nutriku.screen.LoginScreen
import com.example.nutriku.screen.ProfileScreen
import com.example.nutriku.screen.SignUpScreen
import com.example.nutriku.screen.WebViewCompose
import com.example.nutriku.screen.WebViewViewModel
import com.example.nutriku.screen.scan.ContentScan
import com.example.nutriku.screen.scan.SelectActivity
import com.example.nutriku.ui.theme.NutrikuTheme
import com.example.nutriku.viewmodel.ArticleScreenViewModel
import com.example.nutriku.viewmodel.AuthViewModel
import com.example.nutriku.viewmodel.HistoryScreenViewModel
import com.example.nutriku.viewmodel.HomeScreenViewModel
import com.example.nutriku.viewmodel.MyViewModelFactory
import com.example.nutriku.viewmodel.ProfileScreenViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrikuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    navController = rememberNavController()
                    val context = LocalContext.current
                    val owner = this
                    val authViewModel = ViewModelProvider(this, MyViewModelFactory.getInstance(context)).get(AuthViewModel::class.java)

                    NutrikuApp(navController = navController, context = context)
                }
            }
        }
    }
}

@Composable
fun NutrikuApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    //owner: ViewModelStoreOwner
    context: Context
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            val bottomNavigationItems = listOf<NavigationItem>(
                NavigationItem("Home", painterResource(R.drawable.ic_home_selected), painterResource(R.drawable.ic_home_baseline), Screen.Home),
                NavigationItem("Explore", painterResource(R.drawable.ic_explore_selected), painterResource(R.drawable.ic_explore_baseline), Screen.Explore),
                NavigationItem("Scan", painterResource(R.drawable.ic_scan), painterResource(R.drawable.ic_scan), Screen.Scan),
                NavigationItem("History", painterResource(R.drawable.ic_history_selected), painterResource(R.drawable.ic_history_baseline), Screen.History),
                NavigationItem("Profile", painterResource(R.drawable.ic_profile_selected), painterResource(R.drawable.ic_profile_baseline), Screen.Profile),

                )
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val bottomBarDestination = bottomNavigationItems.any { it.screen.route == currentRoute }
            if (bottomBarDestination) {
                FloatingActionButton(
                    onClick = {
//                                navController.navigate(Screen.ScanSelection.route) {
//                                    popUpTo(Screen.Home.route)
//                                }
                                context.startActivity(Intent(context, SelectActivity::class.java))
                              },
                    backgroundColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_scan),
                        contentDescription = "Scan",
                        tint = Color.White
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomBar(navController, modifier) },
        modifier = modifier,
    ) { innerPadding ->
        val context = LocalContext.current
        //val authViewModel = ViewModelProvider(owner, MyViewModelFactory.getInstance(context)).get(AuthViewModel::class.java)

        // ViewModels
        val homeScreenViewModel = HomeScreenViewModel()
        val articleScreenViewModel = ArticleScreenViewModel()
        val historyScreenViewModel = HistoryScreenViewModel()
        val profileScreenViewModel = ProfileScreenViewModel()

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding),
        ) {
            // main screens
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    homeScreenViewModel = homeScreenViewModel,
                    snackbarDisplay = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Fitur ini masih dalam pembangunan",
                                actionLabel = "TUTUP",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }
            composable(Screen.Explore.route) {
                ArticleScreen(navController = navController, articleScreenViewModel = articleScreenViewModel)
            }
            composable(Screen.Scan.route) {
                // ScanSelection()
            }
            composable(Screen.History.route) {
                HistoryScreen(navController = navController, historyScreenViewModel = historyScreenViewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController, profileScreenViewModel = profileScreenViewModel)
            }

            // auth screens
            composable(Screen.Login.route) {
                LoginScreen()
            }
            composable(Screen.Signup.route) {
                SignUpScreen()
            }

            // scan screens
            composable(Screen.ScanSelection.route) {
                //ContentScan(navController = navController)
//                val context = LocalContext.current
//                context.startActivity(Intent(context, SelectActivity::class.java))
                // SelectActivityCompose(navController = navController)
            }
            composable(
                route = Screen.WebView.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType } )
            ) {
                val id = it.arguments?.getInt("id") ?: 1
                val webViewViewModel = WebViewViewModel(id = id)
                WebViewCompose(id = id)
            }

            //composable(Screen.WebView.route, arguments = listOf(navArgument("texturl") {type = NavType.StringType}, navArgument("imgurl") {type = NavType.StringType}) )
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NutrikuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //NutrikuApp(ViewModelStoreOwner)
        }
    }
}


