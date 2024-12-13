package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class AddOrderRequest (
    @SerializedName("address_id") val addressId: Int,
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("use_points") val usePoints: Int,
)

