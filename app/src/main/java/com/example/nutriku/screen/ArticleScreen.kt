package com.example.nutriku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myjetpackapplication.R
import com.example.nutriku.data.ArticleData
import com.example.nutriku.navigation.Screen
import com.example.nutriku.viewmodel.ArticleScreenViewModel

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    articleScreenViewModel: ArticleScreenViewModel
) {
    val articleData by articleScreenViewModel.articleList.collectAsState()

    LazyColumn(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = articleData, key = {it?.id.toString()} ) {data ->
            ArticleCard(modifier = modifier, articleData = data, onClick = {
                navController.navigate(Screen.WebView.createRoute(data?.id?.toInt()!!))
            })
        }
    }
}

@Composable
fun ArticleCard(modifier: Modifier, articleData: ArticleData?, onClick: () -> Unit) {
    if (articleData != null) {
        Card(modifier = modifier
            .fillMaxWidth()
            .height(224.dp)
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable {
                onClick()
            },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            AsyncImage(
                model = articleData.img ?: articleData.imag, contentDescription = "article ${articleData.id} image",
                modifier = modifier
                    .fillMaxWidth()
                    .height(124.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.article_placeholder)
            )
            Text(text = articleData.title.toString(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, modifier = modifier.padding(16.dp), overflow = TextOverflow.Ellipsis)
        }
    }
}

val dummyDataArticles = listOf<ArticleData>(
    ArticleData("https://storage.googleapis.com/article-nutriku/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang/Img-Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang.jpg", "3", "Mengatur porsi makanan dan gaya hidup seimbang sesuai gaya hidup 4 sehat 5 sempurna", "https://storage.googleapis.com/article-nutriku/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang.txt", "https://storage.googleapis.com/article-nutriku/Membangun%20sistem%20kekebalan%20tubuh%20yang%20kuat/Img-Membangun%20sistem%20kekebalan%20tubuh%20yang%20kuat.jpg")
)

@Preview(showSystemUi = true)
@Composable
fun ArticleScreenPrev() {
    ArticleScreen(Modifier, rememberNavController(), ArticleScreenViewModel())
}