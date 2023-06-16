package com.example.nutriku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.ArticleData
import com.example.nutriku.data.ArticleListResponse
import com.example.nutriku.data.Foods
import com.example.nutriku.data.FoodsListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleScreenViewModel: ViewModel() {
    private val _articleList = MutableStateFlow<MutableList<ArticleData?>>(mutableListOf())
    val articleList : StateFlow<List<ArticleData?>> get() = _articleList

    init {
        getAllArticle()
    }

    fun getAllArticle() {
        val model = mutableListOf<ArticleData?>()
        val client = ApiConfig.getApiService().getAllArticle()
        client.enqueue(object: Callback<ArticleListResponse> {
            override fun onResponse(
                call: Call<ArticleListResponse>,
                response: Response<ArticleListResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.addAll(responseBody.data)
                    Log.d("ArticleScreen", "food list: ${model[0]}")
                } else {
                    Log.e("ArticleScreen", "onFailure: ${responseBody?.message}")
                }
                _articleList.value = model
                Log.d("ArticleScreen", "_foodsList: ${articleList.value}")
            }

            override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {
                Log.e("ArticleScreen", "${t.message}")
            }
        })
    }
}