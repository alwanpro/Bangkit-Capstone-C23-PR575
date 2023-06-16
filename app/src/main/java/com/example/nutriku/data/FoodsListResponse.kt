package com.example.nutriku.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FoodsListResponse(

	@field:SerializedName("metadata")
	val metadata: FoodsListMetadata? = null,

	@field:SerializedName("data")
	val data: List<Foods>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class FoodsListMetadata(

	@field:SerializedName("limit")
	val limit: String? = null,

	@field:SerializedName("page")
	val page: Int? = null
) : Parcelable

@Parcelize
data class Foods(

	@field:SerializedName("carb")
	val carb: Float? = null,

	@field:SerializedName("default_amount")
	val defaultAmount: Int? = null,

	@field:SerializedName("food_class")
	val foodClass: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("protein")
	val protein: Float? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("calorie")
	val calorie: Int? = null,

	@field:SerializedName("fat")
	val fat: Float? = null,

	@field:SerializedName("nutriscore")
	val nutriscore: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
