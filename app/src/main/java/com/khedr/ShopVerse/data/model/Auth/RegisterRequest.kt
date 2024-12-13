package com.khedr.ShopVerse.data.model.Auth

data class RegisterRequest(
    val email: String,
    val name: String,
    val phone: String,
    val password: String
)
