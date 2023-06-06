package com.example.nutriku

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.R
import com.example.nutriku.component.BottomBar
import com.example.nutriku.navigation.Screen
import com.example.nutriku.screen.LoginScreen
import com.example.nutriku.ui.theme.NutrikuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrikuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NutrikuApp()
                }
            }
        }
    }
}

@Composable
fun NutrikuApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_scan),
                    contentDescription = "Scan",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomBar(/*navController*/) },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                LoginScreen()
            }
            composable(Screen.Profile.route) {
                // ProfileScreen()
            }
            composable(Screen.Scan.route) {
                // ScanScreen()
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NutrikuTheme {
        NutrikuApp()
    }
}


