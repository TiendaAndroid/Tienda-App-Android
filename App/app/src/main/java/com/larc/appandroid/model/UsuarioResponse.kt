package com.larc.appandroid.model

data class UsuarioResponse(
    val id: String,
    val email: String,
    val direction: List<Direction>?,
    val cart: Cart,
    val orders: List<Order>?,
    val token: String
)

data class UsuarioResponseRegister(
    val email: String,
    val name: String,
    val lastName: String,
    val googleId: String?,
    val id: String,
    val isActive: Boolean,
    val role: List<Order>?,
    val token: String
)

data class ProfileResponse(
    val id: String,
    val email: String,
    val name: String,
    val lastName: String,
    val googleId: String?,
    val isActive: Boolean,
    val role: List<String>,
    val direction: List<Direction>?,
    val cart: Cart,
    val orders: List<Order>?
)
