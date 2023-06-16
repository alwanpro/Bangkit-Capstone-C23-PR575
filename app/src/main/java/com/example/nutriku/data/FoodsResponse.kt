package com.example.nutriku.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FoodsResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class FoodsItem(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("food_class")
	val foodClass: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("nutriscore")
	val nutriscore: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("foods")
	val foods: List<FoodsItem?>? = null
) : Parcelable
