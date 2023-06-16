package com.example.nutriku.data

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: UserProfileData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class UserProfileData(

	@field:SerializedName("gender")
	var gender: String? = null,

	@field:SerializedName("birth_date")
	var birthDate: String? = null,

	@field:SerializedName("weight_category")
	var weightCategory: String? = null,

	@field:SerializedName("weight")
	var weight: Int? = null,

	@field:SerializedName("created_at")
	var createdAt: String? = null,

	@field:SerializedName("target")
	var target: String? = null,

	@field:SerializedName("updated_at")
	var updatedAt: String? = null,

	@field:SerializedName("user_id")
	var userId: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("id")
	var id: String? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("age")
	var age: Int? = null,

	@field:SerializedName("height")
	var height: Int? = null
)
