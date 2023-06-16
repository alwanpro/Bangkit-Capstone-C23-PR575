package com.example.nutriku.data

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<AuthResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("username") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String,
    ) : Call<AuthResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("daily_calorie")
    fun dailyCalorie() : Call<DailyCalorieResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("foods")
    fun getFoodsList(
        @Query("limit") limit: Int,
        @Query("query") query: String,
    ) : Call<FoodsListResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("foods/{food_class}")
    fun getFoodsDetail(
        @Path("food_class") food_class: String,
    ) : Call<FoodsDetailResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("history")
    fun getFoodsHistory(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ) : Call<HistoryResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("article")
    fun getAllArticle() : Call<ArticleListResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("article/{id}")
    fun getArticle(
        @Path("id") id : Int
    ) : Call<ArticleResponse>

    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @GET("profile")
    fun getUserProfile() : Call<UserResponse>


    @FormUrlEncoded
    @Headers("Authorization: token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJmNmZhOWIzOS1jY2M2LTRhMzUtYjhkOC1kZTk5OTA5MTRjZWEiLCJlbWFpbCI6InJpZmt5bXV0aGFoaGFyaUBnbWFpbC5jb20iLCJpYXQiOjE2ODY4MDA4NzV9.3JdHQgsaE4xiwCaArIwMTCcPj0UTQPEfgmy0eeOmoLY")
    @POST("consumption")
    fun postFoodsConsumption(
        @Field("food_class") foodClass : String,
        @Field("amount") amount : Int,
        @Field("file") file : String

    ) : Call<PostFoodsConsumptionResponse>
}