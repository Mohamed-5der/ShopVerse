package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName
import com.khedr.ShopVerse.data.model.home.Products

data class CartResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: CartData
)

data class CartData(
    @SerializedName("cart_items") val cartItems: List<CartItem>,
    @SerializedName("sub_total") val subTotal: Double,
    @SerializedName("total") val total: Double
)

data class CartItem(
    @SerializedName("id") val id: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("product") val product: Products
)

