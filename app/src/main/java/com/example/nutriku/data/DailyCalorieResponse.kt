package com.example.nutriku.data

import com.google.gson.annotations.SerializedName

data class DailyCalorieResponse(

	@field:SerializedName("data")
	val data: DailyCalorieData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DailyCalorieData(

	@field:SerializedName("current")
	var current: String? = null,

	@field:SerializedName("target")
	var target: String? = null
)
