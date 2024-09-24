package com.larc.appandroid.model

data class UsuarioResponse(
    val id: String,
    val email: String,
    val direction: List<String>,
    val cart: Cart,
    val orders: List<String>,
    val token: String
)
