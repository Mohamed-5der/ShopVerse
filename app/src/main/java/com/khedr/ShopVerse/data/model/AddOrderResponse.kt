package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class AddOrderResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val dataOrder: DataOrder
)
data class DataOrder(
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("cost") val cost: Double,
    @SerializedName("vat") val vat: Double,
    @SerializedName("discount") val discount: Double,
    @SerializedName("points") val points: Double,
    @SerializedName("total") val total: Double,
    @SerializedName("id") val id: Int
)