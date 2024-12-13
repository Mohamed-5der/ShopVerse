package com.khedr.ShopVerse.data.model.Auth

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String,
)
