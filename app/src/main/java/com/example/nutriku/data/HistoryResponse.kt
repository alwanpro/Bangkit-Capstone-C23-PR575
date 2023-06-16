package com.example.nutriku.data

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("data")
	val data: List<HistoryData?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class HistoryData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("consumptions")
	val consumptions: List<ConsumptionsItem?>
)

data class Metadata(

	@field:SerializedName("limit")
	val limit: String? = null,

	@field:SerializedName("page")
	val page: Int? = null
)

data class ConsumptionsItem(

	@field:SerializedName("carb")
	val carb: Any? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("total_calorie")
	val totalCalorie: Int? = null,

	@field:SerializedName("default_amount")
	val defaultAmount: Int? = null,

	@field:SerializedName("food_class")
	val foodClass: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("protein")
	val protein: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("calorie")
	val calorie: Int? = null,

	@field:SerializedName("fat")
	val fat: Any? = null,

	@field:SerializedName("nutriscore")
	val nutriscore: String? = null
)
