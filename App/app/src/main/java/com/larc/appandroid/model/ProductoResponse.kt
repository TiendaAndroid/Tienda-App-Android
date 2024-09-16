package com.larc.appandroid.model

data class ProductoResponse(
    val limit: Int,
    val offset: Int,
    val partialResults: Int,
    val totalResults: Int,
    val data: List<Producto>
)
