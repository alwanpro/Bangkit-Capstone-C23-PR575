package com.example.nutriku.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.R
import com.example.nutriku.ui.theme.NutrikuTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScan(
//    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
    )

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF10383A),
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
                    IconButton(onClick = {
//                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF10383A),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF10383A))
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Content()
                ScanButton(
//                    navController,
                    {
                        // TODO: onClickGallery
                    },
                    {
                        // TODO: onClickPhoto
                    }
                )
            }
        }
    )
}

@Composable
fun Content(

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
        Image(
            painterResource(id = R.drawable.illustrator_scan),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 96.dp)
        )
        Text(
            text = stringResource(R.string.scan_txt_1),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.scan_txt_2),
            textAlign = TextAlign.Center,
            color = Color(0xFF9E9E9E),
            fontSize = 12.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 96.dp)
        )
    }
}

@Composable
fun ScanButton(
//    navController: NavController,
    onClickGallery : () -> Unit,
    onClickPhoto: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.ic_gallery),
                contentDescription = "Gallery",
                modifier = Modifier.size(20.dp)
            )
            Text(stringResource(R.string.btn_txt_gallery), Modifier.padding(start = 10.dp))
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFABD1C6),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.ic_camera),
                contentDescription = "Gallery",
                modifier = Modifier.size(20.dp)
            )
            Text(stringResource(R.string.btn_txt_camera), Modifier.padding(start = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanPreview() {
    NutrikuTheme {
        //ContentScan(navController = rememberNavController())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF10383A)
@Composable
fun ContentScanPreview() {
    NutrikuTheme {
        Content()
    }
}