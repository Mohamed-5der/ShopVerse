package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class AddOrDeleteRequest(
    @SerializedName("product_id") val productId: String
)
