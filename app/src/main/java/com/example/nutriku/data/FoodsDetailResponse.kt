package com.example.nutriku.data

import com.google.gson.annotations.SerializedName

data class FoodsDetailResponse(

	@field:SerializedName("data")
	val data: FoodsDetailData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class FoodsDetailData(

	@field:SerializedName("carb")
	var carb: Any? = null,

	@field:SerializedName("default_amount")
	var defaultAmount: Int? = null,

	@field:SerializedName("food_class")
	var foodClass: String? = null,

	@field:SerializedName("image_url")
	var imageUrl: String? = null,

	@field:SerializedName("protein")
	var protein: Any? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("calorie")
	var calorie: Int? = null,

	@field:SerializedName("fat")
	var fat: Any? = null,

	@field:SerializedName("nutriscore")
	var nutriscore: String? = null,

	@field:SerializedName("id")
	var id: String? = null
)
