package com.example.nutriku.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.R
import com.example.nutriku.navigation.NavigationItem
import com.example.nutriku.navigation.Screen
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
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

    Divider(modifier = modifier.fillMaxWidth().padding(0.dp), thickness = 0.5.dp)
    if (bottomBarDestination) {
        BottomNavigation(
            modifier = modifier.shadow(8.dp, spotColor = Color.Black),
            elevation = 12.dp,
            backgroundColor = Color.White
        )
        {
            bottomNavigationItems.forEachIndexed { index, navigationItem ->
                if (index == 2) {
                    BottomNavigationItem(
                        selected = false,
                        onClick = {},
                        icon = {},
                        enabled = false
                    )
                }
                else {
                    BottomNavigationItem(
                        selected = navigationItem.screen.route == currentRoute,
                        onClick = {
                            navController.navigate(navigationItem.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                        icon = {
//                            if (navigationItem.screen.route == currentRoute) {
                                Icon(painter = navigationItem.iconSelected, contentDescription = navigationItem.title, tint = MaterialTheme.colorScheme.primary)
//                            } else {
//                                Icon(painter = navigationItem.iconUnselected, contentDescription = navigationItem.title, tint = MaterialTheme.colorScheme.primary)
//                            }
                        },
                        label = {
                            Text(text = navigationItem.title, color = Color(0xFFA4A4A4), fontSize = 10.sp)
                        },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = Color(0xFFA4A4A4)
                    )
                }
            }
        }
    }

}



@Preview()
@Composable
fun BottomBarPreview() {
    NutrikuTheme() {
        BottomBar(navController = rememberNavController())
    }
}
