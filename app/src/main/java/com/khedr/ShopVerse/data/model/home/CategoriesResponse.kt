package com.khedr.ShopVerse.data.model.home

import com.google.gson.annotations.SerializedName

data class Categories (

    @SerializedName("status")
    var status: Boolean? = null,

    @SerializedName("message")
    var message: Any? = null,

    @SerializedName("data")
    var data: DataCategories? = null

)

data class DataCategories(

    @SerializedName("current_page")
    var currentPage: Int? = null,

    @SerializedName("data")
    var categories: List<Category>? = null,

    @SerializedName("first_page_url")
    var firstPageUrl: String? = null,

    @SerializedName("from")
    var from: Int? = null,

    @SerializedName("last_page")
    var lastPage: Int? = null,

    @SerializedName("last_page_url")
    var lastPageUrl: String? = null,

    @SerializedName("next_page_url")
    var nextPageUrl: String? = null,

    @SerializedName("path")
    var path: String? = null,

    @SerializedName("per_page")
    var perPage: Int? = null,

    @SerializedName("prev_page_url")
    var prevPageUrl: String? = null,

    @SerializedName("to")
    var to: Int? = null,

    @SerializedName("total")
    var total: Int? = null
)

data class Category(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("image")
    var image: String? = null
)