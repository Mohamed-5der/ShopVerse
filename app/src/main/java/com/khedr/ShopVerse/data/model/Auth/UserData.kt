package com.khedr.ShopVerse.data.model.Auth

import com.google.gson.annotations.SerializedName


data class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val image: String,
    @SerializedName("points") val points: Int,
    @SerializedName("credit") val credit: Int,
    @SerializedName("token") val token: String
)