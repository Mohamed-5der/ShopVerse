package com.khedr.ShopVerse.data.model.home

import com.google.gson.annotations.SerializedName


data class ProductByCategoriesResponse (

    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: CategoriesData

)

data class CategoriesData(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val items: List<Products>,
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

