package com.example.nutriku.screen

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.R
import com.example.nutriku.Utils.DateTime
import com.example.nutriku.component.FoodCard
import com.example.nutriku.data.FoodsItem
import com.example.nutriku.ui.theme.NutrikuTheme
import com.example.nutriku.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel,
    snackbarDisplay: () -> Unit
    ) {
    LaunchedEffect(key1 = homeScreenViewModel.key) {
        homeScreenViewModel.getDailyCalorie()
    }

    // State
    val scrollState = rememberScrollState()
    val currentCalorie by homeScreenViewModel.currentCalorie.collectAsState()
    val maxCalorie by homeScreenViewModel.targetCalorie.collectAsState()
    val foodsList by homeScreenViewModel.foodsList.collectAsState()
    val scope = rememberCoroutineScope()

    val dayPhase = "Pagi"// DateTime().getDayPhase()


    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White)
        .verticalScroll(state = scrollState)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Column {
                Text(text = "Halo,", fontWeight = FontWeight.Light)
                Text(text = "Selamat $dayPhase", fontSize = 24.sp)
            }
            Icon(painter = painterResource(id = R.drawable.ic_nutriku_logo), contentDescription = "Nutriku logo", tint = MaterialTheme.colorScheme.primary, modifier = modifier.size(48.dp))
        }
        Row(modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Column {
                Text(text = "Total Kalori Harian", fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = "$currentCalorie/$maxCalorie Kcal", color = Color.White)
            }
            IconButton(onClick = { /*TODO*/ }, modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_24), contentDescription = "Informasi Lebih Lanjut", tint = MaterialTheme.colorScheme.primary)
            }
        }
        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.clickable {
                    snackbarDisplay()
                }) {
                Image(painter = painterResource(id = R.drawable.ic_nutrifood), contentDescription = "NutriFood", modifier = modifier.size(72.dp))
                Text(text = "NutriFood", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier.clickable {
                    snackbarDisplay()
                }) {
                Image(painter = painterResource(id = R.drawable.ic_nutricook), contentDescription = "NutriCook", modifier = modifier.size(72.dp))
                Text(text = "NutriCook", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier.clickable {
                    snackbarDisplay()
                }) {
                Image(painter = painterResource(id = R.drawable.ic_nutridiabet), contentDescription = "NutriDiabet", modifier = modifier.size(72.dp))
                Text(text = "NutriDiabet", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier.clickable {
                    snackbarDisplay()
                }) {
                Image(painter = painterResource(id = R.drawable.ic_nutrialergic), contentDescription = "NutriAlergic", modifier = modifier.size(72.dp))
                Text(text = "NutriAlergic", style = MaterialTheme.typography.bodySmall)
            }
        }

        Image(painter = painterResource(R.drawable.banner_1), contentDescription = "Banner", modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(8.dp)
            ))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            Row(modifier = modifier
                .fillMaxWidth()
                .clickable { /* TODO:  */ },
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Cari Makanan", fontSize = 20.sp, )

                Surface(
                    shape = RoundedCornerShape(size = 5.dp),
                    border = BorderStroke(1.dp, Color(0xFF10383A)),
                    color = Color.Transparent,
                    contentColor = Color(0xFF10383A),
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_24), contentDescription = "cari makanan button", modifier = modifier
                        .size(24.dp)
                        .padding(4.dp)
                    )
                }

            }
            LazyRow(modifier = modifier
                .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                items(items = foodsList, key = {it?.id.toString()} ) {
                    FoodCard(modifier = Modifier, foodItem = it, onClick = {})
                }
            }
        }
    }

}

val dummyData = listOf<FoodsItem>(FoodsItem("100", "ayam_geprek", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg", "Ayam Geprek", "B", "1"), FoodsItem("100", "ayam_geprek", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX67_CR0,0,67,98_AL_.jpg", "Ayam Geprek", "B", "2"))

@Preview()
@Composable
fun HomeScreenPreview() {
    NutrikuTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //HomeScreen(navController = rememberNavController(), homeScreenViewModel = HomeScreenViewModel())
        }

    }
}