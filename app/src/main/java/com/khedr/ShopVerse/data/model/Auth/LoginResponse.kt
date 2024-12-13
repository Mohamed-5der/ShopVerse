package com.khedr.ShopVerse.data.model.Auth

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UserData?
)

