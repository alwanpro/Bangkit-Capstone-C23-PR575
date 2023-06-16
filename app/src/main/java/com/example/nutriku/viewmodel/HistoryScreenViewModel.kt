package com.example.nutriku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.ArticleData
import com.example.nutriku.data.ArticleListResponse
import com.example.nutriku.data.HistoryData
import com.example.nutriku.data.HistoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryScreenViewModel : ViewModel() {
    private val _historyList = MutableStateFlow<MutableList<HistoryData?>>(mutableListOf())
    val historyList : StateFlow<List<HistoryData?>> get() = _historyList

    init {
        getAllHistory()
    }

    fun getAllHistory() {
        val model = mutableListOf<HistoryData?>()
        val client = ApiConfig.getApiService().getFoodsHistory(10, 0)
        client.enqueue(object: Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.addAll(responseBody.data)
                    Log.d("HistoryScreen", "food list: ${model[0]}")
                } else {
                    Log.e("HistoryScreen", "onFailure: ${responseBody?.message}")
                }
                _historyList.value = model
                Log.d("HistoryScreen", "_foodsList: ${_historyList.value}")
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.e("HistoryScreen", "${t.message}")
            }
        })
    }
}