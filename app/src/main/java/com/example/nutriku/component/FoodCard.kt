package com.example.nutriku.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myjetpackapplication.R
import com.example.nutriku.data.Foods
import com.example.nutriku.data.FoodsItem
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun FoodCard(modifier: Modifier, foodItem: Foods?, onClick : (String) -> Unit) {
    Card(modifier = modifier
        .clickable {
            foodItem?.foodClass?.let { onClick(it) }
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        if (foodItem != null) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier.padding(8.dp)) {
                AsyncImage(
                    model = foodItem.imageUrl,
                    contentDescription = foodItem.name,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(width = 144.dp, height = 72.dp).clip(RoundedCornerShape(8.dp)),
                    placeholder = painterResource(id = R.drawable.foods_placeholder)
                )
                Text(text = foodItem.name.orEmpty(), fontWeight = FontWeight.Bold)
                Text(text = "Nutriscore ${ foodItem.nutriscore }")
            }
        }
    }
}

@Preview
@Composable
fun FoodCardPrev() {
    NutrikuTheme {
        FoodCard(modifier = Modifier, foodItem = Foods(100f, 100,  "Ayam Geprek","https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg", 24f, "Ayam Geprek", 100, 50f, "B", "001"), onClick = {})
    }
}

