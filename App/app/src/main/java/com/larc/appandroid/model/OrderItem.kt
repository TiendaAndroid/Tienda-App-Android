package com.larc.appandroid.model

data class OrderItem(
    val id: String,
    val quantity: Int,
    val addedAt: String,
    val product: Producto,
)
