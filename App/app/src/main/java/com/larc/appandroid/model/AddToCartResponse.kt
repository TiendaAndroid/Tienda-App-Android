package com.larc.appandroid.model

data class AddToCartResponse(
    val quantity: Int,
    val cart: Cart,
    val product: Producto,
    val id: String,
    val addedAt: String,
)
