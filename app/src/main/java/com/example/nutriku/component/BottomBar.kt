package com.example.nutriku.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myjetpackapplication.R
import com.example.nutriku.navigation.NavigationItem
import com.example.nutriku.navigation.Screen
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun BottomBar(
    //navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val bottomNavigationItems = listOf<NavigationItem>(
        NavigationItem("Home", painterResource(R.drawable.ic_home_baseline), Screen.Home),
        NavigationItem("Explore", painterResource(R.drawable.ic_explore_baseline), Screen.Explore),
        NavigationItem("Scan", painterResource(R.drawable.ic_scan), Screen.Scan),
        NavigationItem("History", painterResource(R.drawable.ic_history_baseline), Screen.Home),
        NavigationItem("Profile", painterResource(R.drawable.ic_profile_baseline), Screen.Profile),

        )
    val contextForToast = LocalContext.current.applicationContext
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        modifier = modifier,
        cutoutShape = CircleShape,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
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
                    selected = (navigationItem.title == selectedItem),
                    onClick = {},
                    icon = {
                        Icon(painter = navigationItem.icon, contentDescription = navigationItem.title)
                    },
                    label = {
                        Text(text = navigationItem.title, color = Color(0xFFA4A4A4), fontSize = 8.sp)
                    }
                )

            }
        }
    }
}



@Preview()
@Composable
fun BottomBarPreview() {
    NutrikuTheme() {
        BottomBar()
    }
}
