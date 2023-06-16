package com.example.nutriku.data

import com.google.gson.annotations.SerializedName

data class PostFoodsConsumptionResponse(

	@field:SerializedName("data")
	val data: PostFoodsConsumptionData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class PostFoodsConsumptionData(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("food_class")
	val foodClass: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("total_calorie")
	val totalCalorie: Int? = null
)
