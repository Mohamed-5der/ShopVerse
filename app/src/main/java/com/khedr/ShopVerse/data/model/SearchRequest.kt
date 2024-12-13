package com.khedr.ShopVerse.data.model

import com.google.gson.annotations.SerializedName

data class SearchRequest(
    @SerializedName("text") val text: String
)
