package com.example.nutriku.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjetpackapplication.R
import com.example.nutriku.ui.theme.NutrikuTheme


val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_regular, FontWeight.Normal)
)


@Composable
fun Count(
) {

    val counter = remember { mutableStateOf(100) }
    Row(
        modifier = Modifier
            .size(width = 140.dp, height = 40.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, Color(0xFF10383A)),
            color = Color.Transparent,
            contentColor = Color(0xFF10383A),
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "-",
                fontSize = 22.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (counter.value > 100) {
                            counter.value -= 100
                        }
                    },
                color = Color(0xFF10383A)
            )
        }
        Text(
            text = "${counter.value}g",
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, Color(0xFF10383A)),
            color = Color.Transparent,
            contentColor = Color(0xFF10383A),
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "+",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (counter.value < 1000) {
                            counter.value += 100
                        }
                    },
                color = Color(0xFF10383A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountPreview() {
    NutrikuTheme {
        Count()
    }
}