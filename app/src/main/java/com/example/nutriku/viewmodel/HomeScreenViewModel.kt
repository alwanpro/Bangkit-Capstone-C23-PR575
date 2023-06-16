package com.example.nutriku.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.AuthResponse
import com.example.nutriku.data.DailyCalorieData
import com.example.nutriku.data.DailyCalorieResponse
import com.example.nutriku.data.Foods
import com.example.nutriku.data.FoodsItem
import com.example.nutriku.data.FoodsListResponse
import com.example.nutriku.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel: ViewModel() {
    private val _userDetail = MutableStateFlow<UserData>(UserData())
    val userDetail : StateFlow<UserData> get() = _userDetail.asStateFlow()

    private val _currentCalorie = MutableStateFlow<Int>(0)
    val currentCalorie : StateFlow<Int> get() = _currentCalorie

    private val _targetCalorie = MutableStateFlow<Int>(1000)
    val targetCalorie : StateFlow<Int> get() = _targetCalorie

    private val _foodsList = MutableStateFlow<MutableList<Foods?>>(mutableListOf())
    val foodsList : StateFlow<List<Foods?>> get() = _foodsList

    var key : Int = 0

    init {
        getDailyCalorie()
        getFoodsList()
        key += 1
    }

//    fun getUserDetail() {
//        val client = ApiConfig.getApiService().login(name, password)
//        client.enqueue(object: Callback<AuthResponse> {
//            override fun onResponse(
//                call: Call<AuthResponse>,
//                response: Response<AuthResponse>
//            ) {
//                val responseBody = response.body()
//                if (response.isSuccessful && responseBody?.data != null) {
//                    // TODO: save token and go to home screen
//                } else {
//                    Log.e("LoginScreen", "onFailure: ${responseBody?.message}")
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.e("LoginScreen", "Login Error")
//            }
//        })
//    }

    fun getDailyCalorie() {
        val model = DailyCalorieData()
        val client = ApiConfig.getApiService().dailyCalorie()
        client.enqueue(object: Callback<DailyCalorieResponse> {
            override fun onResponse(
                call: Call<DailyCalorieResponse>,
                response: Response<DailyCalorieResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.current = responseBody.data.current
                    model.target = responseBody.data.target
                    Log.d("HomeScreen", "current calorie: ${model.current}, target calorie: ${model.target}")
                } else {
                    Log.e("HomeScreen", "onFailure: ${responseBody?.message}")
                }

                _currentCalorie.value = model.current?.toInt() ?: 0
                _targetCalorie.value = model.target?.toInt() ?: 0
                Log.d("HomeScreen", "current calorie: ${currentCalorie.value}, target calorie: ${targetCalorie.value}")

                key += 1
            }

            override fun onFailure(call: Call<DailyCalorieResponse>, t: Throwable) {
                Log.e("HomeScreen", "getDailyCalorie: ${t.message}")
            }
        })

//        _currentCalorie.value = model.current?.toInt() ?: 0
//        _targetCalorie.value = model.target?.toInt() ?: 0

//        Log.d("HomeScreen", "current calorie: ${currentCalorie.value}, target calorie: ${targetCalorie.value}")
    }

    fun getFoodsList() {
        val model = mutableListOf<Foods?>()
        val client = ApiConfig.getApiService().getFoodsList(4, "")
        client.enqueue(object: Callback<FoodsListResponse> {
            override fun onResponse(
                call: Call<FoodsListResponse>,
                response: Response<FoodsListResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.addAll(responseBody.data)
                    Log.d("HomeScreen", "food list: ${model[0]}")
                } else {
                    Log.e("HomeScreen", "onFailure: ${responseBody?.message}")
                }
                _foodsList.value = model
                Log.d("HomeScreen", "_foodsList: ${foodsList.value}")

                key += 1
            }

            override fun onFailure(call: Call<FoodsListResponse>, t: Throwable) {
                Log.e("HomeScreen", "${t.message}")
            }
        })

//        _foodsList.value.removeAll(model)
//        Log.d("HomeScreen", "_foodsList: ${foodsList.value}")
    }

    fun returnFoodsList() : List<Foods?> {
        val model = mutableListOf<Foods?>()
        val client = ApiConfig.getApiService().getFoodsList(4, "")
        client.enqueue(object: Callback<FoodsListResponse> {
            override fun onResponse(
                call: Call<FoodsListResponse>,
                response: Response<FoodsListResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.addAll(responseBody.data)
                    Log.d("HomeScreen", "food list: ${model[0]}")
                } else {
                    Log.e("HomeScreen", "onFailure: ${responseBody?.message}")
                }
            }

            override fun onFailure(call: Call<FoodsListResponse>, t: Throwable) {
                Log.e("HomeScreen", "${t.message}")
            }
        })

       return model.toList()
    }

    fun returnCurrentCalorie(): Int {
        val model = DailyCalorieData()
        val client = ApiConfig.getApiService().dailyCalorie()
        client.enqueue(object: Callback<DailyCalorieResponse> {
            override fun onResponse(
                call: Call<DailyCalorieResponse>,
                response: Response<DailyCalorieResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.current = responseBody.data.current
                    model.target = responseBody.data.target
                } else {
                    Log.e("HomeScreen", "onFailure: ${responseBody?.message}")
                }
            }

            override fun onFailure(call: Call<DailyCalorieResponse>, t: Throwable) {
                Log.e("HomeScreen", "${t.message}")
            }
        })
        return model.current?.toInt() ?: 0
    }

    fun returnTargetCalorie(): Int {
        val model = DailyCalorieData()
        val client = ApiConfig.getApiService().dailyCalorie()
        client.enqueue(object: Callback<DailyCalorieResponse> {
            override fun onResponse(
                call: Call<DailyCalorieResponse>,
                response: Response<DailyCalorieResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.current = responseBody.data.current
                    model.target = responseBody.data.target
                } else {
                    Log.e("HomeScreen", "onFailure: ${responseBody?.message}")
                }
            }

            override fun onFailure(call: Call<DailyCalorieResponse>, t: Throwable) {
                Log.e("HomeScreen", "${t.message}")
            }
        })
        return model.target?.toInt() ?: 0
    }
}