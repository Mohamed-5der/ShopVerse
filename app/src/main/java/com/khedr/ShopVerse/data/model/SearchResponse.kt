package com.khedr.ShopVerse.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: Data
)

data class Data(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val products: List<Product>,
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

