package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class AddOrDeleteFavoriteResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: ProductDetails
)

data class  ProductDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("product") val product: ProductAdd
)

data class ProductAdd(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("old_price") val oldPrice: Int,
    @SerializedName("discount") val discount: Int,
    @SerializedName("image") val image: String
)