package com.larc.appandroid.model

data class AddToCartResponse(
    val quantity: Int,
    val cart: String,
    val product: String,
    val id: String,
    val addedAt: String,
)
