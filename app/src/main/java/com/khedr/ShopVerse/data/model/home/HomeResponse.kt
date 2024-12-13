package com.khedr.ShopVerse.data.model.home

import com.google.gson.annotations.SerializedName

data class HomeResponse (

    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data?    = Data()

)