package com.example.nutriku.component

import android.graphics.Paint
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle

@Composable
fun TextLink(modifier: Modifier, text: String, onClick: () -> Unit) {
    val underlinedString = SpannableString(text)
    underlinedString.setSpan(UnderlineSpan(), 0, underlinedString.length, 0)

    Text(
        text = underlinedString.toString(),
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .clickable {
                onClick()
            })

}