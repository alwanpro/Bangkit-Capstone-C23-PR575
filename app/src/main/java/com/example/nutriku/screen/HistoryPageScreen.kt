package com.example.nutriku.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myjetpackapplication.R
import com.example.nutriku.data.ConsumptionsItem
import com.example.nutriku.data.Foods
import com.example.nutriku.data.HistoryData
import com.example.nutriku.screen.ArticleCard
import com.example.nutriku.viewmodel.ArticleScreenViewModel
import com.example.nutriku.viewmodel.HistoryScreenViewModel

@Composable
fun DateHistory(text: String) {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun CardHistory(modifier : Modifier = Modifier, data : ConsumptionsItem?) {
    if (data != null) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(corner = CornerSize(8.dp))
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = data.name,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(width = 72.dp, height = 72.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    placeholder = painterResource(id = R.drawable.foods_placeholder)
                )

                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Nutriscore : ${data.nutriscore}", fontSize = 12.sp)
                        Text(text = "${data.amount}g", fontSize = 12.sp)
                    }
                    Text(text = data.name.orEmpty(), fontSize = 16.sp)
                    Text(text = "Cal: ${data.calorie}, Fat: ${data.fat}, Carb: ${data.carb}, \nProt:${data.protein}, Kal:${data.calorie},", fontSize = 12.sp)
                }

            }
        }
    }
}


@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    historyScreenViewModel: HistoryScreenViewModel
) {
    SideEffect {
        historyScreenViewModel.getAllHistory()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        val historyList by historyScreenViewModel.historyList.collectAsState()

        if (historyList.isNotEmpty()) {
            historyList.map { historyData ->
                if(historyData != null) {
                    DateHistory(text = historyData.date.toString())
                    LazyColumn(modifier = modifier
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = historyData.consumptions, key = {"${historyData.date}_${it?.foodClass}"} ) {consumptionItem ->
                            CardHistory(data = consumptionItem)
                        }
                    }
                }
            }
        } else {
            Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "No consumption history", color = Color.DarkGray)
            }
        }
    }
}

@Preview
@Composable
fun HistoryComponentPrev() {
    DateHistory(text = "17 April 2023")
    //CardHistory(data = ConsumptionsItem(carb = 100f, 100,  "ayam_geprek","https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg", 200, 100,"Ayam Geprek", 50f, "B"))
}