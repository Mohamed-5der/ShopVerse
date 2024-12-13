package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class DetailsProductResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: ProductData
)

data class ProductData(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("old_price") val oldPrice: Int,
    @SerializedName("discount") val discount: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("in_favorites") val inFavorites: Boolean,
    @SerializedName("in_cart") val inCart: Boolean,
    @SerializedName("images") val images: List<String>
)
