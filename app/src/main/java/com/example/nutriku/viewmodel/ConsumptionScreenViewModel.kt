package com.example.nutriku.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.FoodsDetailData
import com.example.nutriku.data.FoodsDetailResponse
import com.example.nutriku.data.PostFoodsConsumptionResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File


class ConsumptionScreenViewModel(val foodclass: String): ViewModel() {
    private val _foodsDetail = MutableLiveData<FoodsDetailData?>()
    val foodsDetail : LiveData<FoodsDetailData?> get() = _foodsDetail
    val selectedAmount = MutableLiveData(100)
    val uploadStatus = MutableLiveData(false)

    init {
        getFoodsDetail(foodclass)
    }
    fun postFoodsConsumption(imgPath : String, foodclass: String, amount: String, onCallBack: () -> Unit) {
//        viewModelScope.launch {

        val file : File = File(imgPath)
//        val imagePart = MultipartBody.Part.createFormData(name = "file", filename = file.name, body = file.asRequestBody())

        val bitMap = BitmapFactory.decodeFile(imgPath)
        val baos = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        val encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)
        Log.d("ConsumptionScreen", "Encoded Image: $encodedImage")
        Log.d("ConsumptionScreen", "foodclass: $foodclass")
        Log.d("ConsumptionScreen", "amount: ${selectedAmount.value}")

//        val foodclassPart = MultipartBody.Part.createFormData("food_class", foodclass)
//        val amountPart = MultipartBody.Part.createFormData("amount", amount)

//            val client = OkHttpClient()
//            val mediaType = "text/plain".toMediaType()
//            val body = MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("food_class",foodclass)
//                .addFormDataPart("amount",amount)
////                .addFormDataPart("file",file.name, file.asRequestBody())
//                .build()
//            val policy = ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//            val request = Request.Builder()
//                .url("https://capstone-nutriku-project.et.r.appspot.com/api/consumption")
//                .post(body)
//                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
//                .build()
//            val response = client.newCall(request).execute()
//            Log.d("ConsumptionScreen", response.message)
//            Log.d("ConsumptionScreen", response.message)
//            onCallBack()
//        }


//        viewModelScope.launch {
//
            val client = ApiConfig.getApiService().postFoodsConsumption(foodclass, selectedAmount.value ?: 0, encodedImage)
            client.enqueue(object: Callback<PostFoodsConsumptionResponse> {
                override fun onResponse(
                    call: Call<PostFoodsConsumptionResponse>,
                    response: Response<PostFoodsConsumptionResponse>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody?.data != null) {

                        uploadStatus.value = true

                        Log.d("ConsumptionScreen", "food img: ${responseBody.data.imageUrl}")
                    } else {
                        Log.e("ConsumptionScreen", "onFailure: ${responseBody?.message}")
                    }
                    onCallBack()
                }


                override fun onFailure(call: Call<PostFoodsConsumptionResponse>, t: Throwable) {
                    Log.e("ConsumptionScreen", "${t.message}")
                }
            })
//        }
    }


    fun getFoodsDetail(foodclass: String) {
        val model = FoodsDetailData()
        val client = ApiConfig.getApiService().getFoodsDetail(foodclass)
        client.enqueue(object: Callback<FoodsDetailResponse> {
            override fun onResponse(
                call: Call<FoodsDetailResponse>,
                response: Response<FoodsDetailResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody?.data != null) {
                    model.carb = responseBody.data.carb
                    model.defaultAmount = responseBody.data.defaultAmount
                    model.foodClass = responseBody.data.foodClass
                    model.imageUrl = responseBody.data.imageUrl
                    model.protein = responseBody.data.protein
                    model.name = responseBody.data.name
                    model.calorie = responseBody.data.calorie
                    model.fat = responseBody.data.fat
                    model.nutriscore = responseBody.data.nutriscore
                    model.id = responseBody.data.id

                    Log.d("ConsumptionScreen", "food list: ${model.name}")
                } else {
                    Log.e("ConsumptionScreen", "onFailure: ${responseBody?.message}")
                }
                _foodsDetail.value = model
                Log.d("ConsumptionScreen", "_foodsList: ${foodsDetail.value?.name}")
            }

            override fun onFailure(call: Call<FoodsDetailResponse>, t: Throwable) {
                Log.e("ConsumptionScreen", "${t.message}")
            }
        })
    }

}