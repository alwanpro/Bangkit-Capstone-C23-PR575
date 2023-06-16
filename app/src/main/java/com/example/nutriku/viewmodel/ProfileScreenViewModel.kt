package com.example.nutriku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.ArticleData
import com.example.nutriku.data.ArticleListResponse
import com.example.nutriku.data.UserProfileData
import com.example.nutriku.data.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileScreenViewModel: ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfileData>( UserProfileData() )
    val userProfile : StateFlow<UserProfileData> get() = _userProfile
    var key : Int = 0

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        val model = UserProfileData()
        val client = ApiConfig.getApiService().getUserProfile()
        client.enqueue(object: Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.id = responseBody.data.id
                    model.userId = responseBody.data.userId
                    model.weight = responseBody.data.weight
                    model.height = responseBody.data.height
                    model.gender = responseBody.data.gender
                    model.birthDate = responseBody.data.birthDate
                    model.target = responseBody.data.target
                    model.weightCategory = responseBody.data.weightCategory
                    model.createdAt = responseBody.data.createdAt
                    model.updatedAt = responseBody.data.updatedAt
                    model.name = responseBody.data.name
                    model.email = responseBody.data.email
                    model.age = responseBody.data.age
                    Log.d("ProfileScreen", "food list: ${model.name}")
                } else {
                    Log.e("ProfileScreen", "onFailure: ${responseBody?.message}")
                }
                _userProfile.value = model
                Log.d("ProfileScreen", "_foodsList: ${userProfile.value.name}")
                key++
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("ProfileScreen", "${t.message}")
            }
        })
    }

}