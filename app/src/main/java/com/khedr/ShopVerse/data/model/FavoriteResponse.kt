package com.khedr.ShopVerse.data.model
import com.google.gson.annotations.SerializedName

data class FavoriteResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: FavoritesData
)

data class FavoritesData(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val items: List<FavoriteItem>,
    @SerializedName("first_page_url") val firstPageUrl: String,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("last_page_url") val lastPageUrl: String,
    @SerializedName("next_page_url") val nextPageUrl: String?,
    @SerializedName("path") val path: String,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("prev_page_url") val prevPageUrl: String?,
    @SerializedName("to") val to: Int,
    @SerializedName("total") val total: Int
)

data class FavoriteItem(
    @SerializedName("id") val id: Int,
    @SerializedName("product") val product: Product
)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("old_price") val oldPrice: Double,
    @SerializedName("discount") val discount: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)
