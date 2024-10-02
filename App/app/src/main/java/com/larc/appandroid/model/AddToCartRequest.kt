package com.larc.appandroid.model

data class AddToCartRequest(
    val cart: String,
    val product: String,
    val quantity: Int,
)
