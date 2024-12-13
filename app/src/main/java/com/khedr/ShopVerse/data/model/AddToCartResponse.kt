package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName
import com.khedr.ShopVerse.data.model.home.Products

data class AddToCartResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: AddToCartData
)

data class AddToCartData(
    @SerializedName("id") val id: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("product") val product: Products
)