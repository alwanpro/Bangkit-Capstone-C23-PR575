package com.example.nutriku.screen.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myjetpackapplication.R
import com.example.nutriku.ui.theme.NutrikuTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SavePage(){
    val systemUiController = rememberSystemUiController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color.White,
            )
        }
        Save()
        SaveButton()

    }
}

@Composable
fun Save(){
    val com by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success))
    Box(modifier = Modifier.fillMaxWidth()){
        LottieAnimation(
            composition = com,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            iterations = LottieConstants.IterateForever,
            )
        }
    SavePage1()
}

@Composable
fun SavePage1(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.save_txt_1),
            fontFamily = poppinsFontFamily,
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 30.dp),
        )
    }
}

@Composable
fun SaveButton() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10383A),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_txt_home), Modifier.padding(start = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavePreview() {
    NutrikuTheme {
        SavePage()
    }
}

@Preview(showBackground = true)
@Composable
fun SaveButtonPreview() {
    NutrikuTheme {
        SaveButton()
    }
}
