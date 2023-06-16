package com.example.nutriku.screen.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjetpackapplication.R
import com.example.nutriku.component.Count
import com.example.nutriku.ui.theme.NutrikuTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalScan() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
        )
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.scan_txt_top),
                        maxLines = 1,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF10383A)),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(320.dp)
                            .padding(top = 20.dp)
                    ) {
                    }
                    Cal()
                    CalInfo()
                }
                Row() {
                    BottomCal()
                }

//                ScanButton()
            }

        }
    )
}

@Composable
fun Cal(

) {
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(R.font.poppins_regular, FontWeight.Normal)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.result_txt_2),
            fontSize = 32.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(0.9f),
        )
        Text(
            text = "100 g",
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End
        ) {
            Count()
        }

    }
}

@Composable
fun CalInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {
        Text(
            text = stringResource(R.string.result_txt_1),
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        Row() {
            Text(
                text = stringResource(R.string.cal_txt_energy),
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "VALUE kkal",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }
        Row() {
            Text(
                text = stringResource(R.string.cal_txt_fat),
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "VALUE g",
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Row() {
            Text(
                text = stringResource(R.string.cal_txt_protein),
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "VALUE g",
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Row() {
            Text(
                text = stringResource(R.string.cal_txt_carb),
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "VALUE g",
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun BottomCal() {
    Column() {
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(2.dp)
                .background(Color.LightGray),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = "Total Kalori",
                    color = Color.Black,
                    fontFamily = poppinsFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                )
                Row() {
                    Text(
                        text = "VALUE Kcal",
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontFamily = poppinsFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }

            ShortButton()
        }
    }

}

@Composable
fun ShortButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10383A),
                contentColor = Color.White
            ),
            modifier = Modifier
        ) {
            Text(stringResource(R.string.btn_txt_save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalPreview() {
    NutrikuTheme {
        CalScan()
    }
}

@Preview(showBackground = true)
@Composable
fun CalScanPreview() {
    NutrikuTheme {
        Cal()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomCalPreview() {
    NutrikuTheme {
        BottomCal()
    }
}
