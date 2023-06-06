package com.example.nutriku.screen

import android.window.SplashScreen
import android.window.SplashScreenView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjetpackapplication.R
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun SplashScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {
        Image(painter = painterResource(R.drawable.ic_nutriku_logo), contentDescription = "Nutriku", Modifier.size(96.dp))
        Text(text = "Nutriku", fontSize = 60.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colors.onPrimary)
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenView() {
    NutrikuTheme() {
        SplashScreen()
    }
}