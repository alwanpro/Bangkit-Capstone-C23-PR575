package com.example.nutriku.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.R
import com.example.nutriku.navigation.Screen
import com.example.nutriku.ui.theme.NutrikuTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ResultScan(
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF10383A)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color(0xFF10383A),
            )
        }
        Result()
        ResultButton(
            {
                // navArgument
                navController.navigate(Screen.CalScan.route)
            },
            {
                navController.navigateUp()
            }
        )
    }
}

@Composable
fun Result(

    modifier: Modifier = Modifier,
) {
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(R.font.poppins_regular, FontWeight.Normal)
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.result_txt_1),
            textAlign = TextAlign.Center,
            color = Color(0xFF9E9E9E),
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.result_txt_2),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Image(
            painterResource(id = R.drawable.illustrator_scan),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 196.dp)
        )
    }
}

@Composable
fun ResultButton(
    onClickConfirm: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { onClickConfirm },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFABD1C6),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Konfirmasi Foto", Modifier.padding(start = 10.dp))
        }
        TextButton(
            onClick = { onClickBack() },
        ) {
            Text(stringResource(R.string.btn_txt_back), color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    NutrikuTheme {
        ResultScan(rememberNavController())
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF10383A)
@Composable
fun ContentResultPreview() {
    NutrikuTheme {
        Result()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF10383A)
@Composable
fun ButtonResultPreview() {
    NutrikuTheme {
        //ResultButton(rememberNavController())
    }
}