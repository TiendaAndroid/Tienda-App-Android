package com.larc.appandroid.model

data class Cart(
    val id: String,
    val createdAt: String,
    val cartItems: List<CartItem>?
)
