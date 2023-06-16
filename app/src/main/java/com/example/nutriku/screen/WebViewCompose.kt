package com.example.nutriku.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myjetpackapplication.R
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.ArticleData
import com.example.nutriku.data.ArticleListResponse
import com.example.nutriku.data.ArticleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun WebViewCompose(
    modifier: Modifier = Modifier,
    id: Int,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var articleData by remember {
            mutableStateOf(ArticleData())
        }
        LaunchedEffect(key1 = true) {
            val client = ApiConfig.getApiService().getArticle(id)
            client.enqueue(object: Callback<ArticleResponse> {
                override fun onResponse(
                    call: Call<ArticleResponse>,
                    response: Response<ArticleResponse>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody?.data != null) {

                    } else {
                        Log.e("ArticleScreen", "onFailure: ${responseBody?.message}")
                    }
                    articleData = responseBody?.data!!
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    Log.e("ArticleScreen", "${t.message}")
                }
            })
        }

        if(articleData.id != null) {
            AsyncImage(
                model = articleData.img ?: articleData.imag, contentDescription = "article ${articleData.id} image",
                modifier = modifier
                    .fillMaxWidth()
                    .height(248.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.article_placeholder)
            )
            Text(text = articleData.title.toString(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, modifier = modifier.padding(16.dp), overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
            Text(text = articleData.body.toString(), style = MaterialTheme.typography.bodyMedium)

        }
    }

}

class WebViewViewModel(val id: Int): ViewModel() {
    val articleData = MutableStateFlow<ArticleData>( ArticleData() )

    fun getArticle() {
        val client = ApiConfig.getApiService().getArticle(id)
        client.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {

                } else {
                    Log.e("ArticleScreen", "onFailure: ${responseBody?.message}")
                }
                articleData.value = responseBody?.data!!
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.e("ArticleScreen", "${t.message}")
            }
        })
    }

}


