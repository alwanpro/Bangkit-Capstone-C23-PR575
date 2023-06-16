package com.example.nutriku.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ch.benlu.composeform.formatters.upperCase
import com.example.nutriku.component.PrimaryButton
import com.example.nutriku.navigation.Screen
import com.example.nutriku.ui.theme.NutrikuTheme
import com.example.nutriku.viewmodel.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel
) {
    LaunchedEffect(key1 = profileScreenViewModel.key) {
        profileScreenViewModel.getUserProfile()
    }

    val userProfile by profileScreenViewModel.userProfile.collectAsState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color.White),

            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = userProfile.name?.replaceFirstChar { it.uppercase() } ?: "User", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = modifier.padding(bottom = 8.dp))
                Text(text = userProfile.email ?: "example@gmail.com", fontSize = 16.sp, fontStyle = FontStyle.Italic)
                Text(text = "${userProfile.gender?.replaceFirstChar { it.uppercase() } ?: "Gender"}, ${userProfile.age} yrs old", fontSize = 16.sp)
                Text(text = "${userProfile.height} cm | ${userProfile.weight} kg ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Health Category: ${userProfile.weightCategory?.replaceFirstChar { it.uppercase() }}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
        PrimaryButton(modifier = modifier, buttonText = "Exit", onClick = {
            // TODO("Clear preference for token & userId")

            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Login.route)
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPrev() {
    NutrikuTheme {
        ProfileScreen(navController = rememberNavController(), profileScreenViewModel = ProfileScreenViewModel())
    }
}