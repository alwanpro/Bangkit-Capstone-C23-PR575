package com.example.nutriku.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjetpackapplication.R
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun NutrikuBadge(modifier: Modifier) {
    Row(
        modifier = modifier
        .wrapContentWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.ic_nutriku_logo), contentDescription = "Nutriku", Modifier.size(36.dp), colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary))
        Row(modifier = modifier.padding(start = 8.dp)) {
            Text(text = "Nutriku", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun NutrikuBadgePreview() {
    NutrikuTheme() {
        NutrikuBadge(modifier = Modifier)
    }
}