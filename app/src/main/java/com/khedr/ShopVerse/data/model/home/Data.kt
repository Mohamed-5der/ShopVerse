package com.khedr.ShopVerse.data.model.home

import com.google.gson.annotations.SerializedName


data class Data (

    @SerializedName("banners"  ) var banners  : ArrayList<Banners>  = arrayListOf(),
    @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf(),
    @SerializedName("ad"       ) var ad       : String?             = null

)