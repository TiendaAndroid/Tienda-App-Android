package com.larc.appandroid.model

data class DireccionCompletaResponse(
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val colonia: String,
    val cp: Int,
    val user: UserAddressResponse,
    val noInterior: String?,
    val id: String,
)

data class UserAddressResponse(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
    val googleId: String?,
    val isActive: Boolean,
    val role: List<String>,
    val direction: List<Direction>?,
    val cart: Cart,
    val orders: List<Order>?
)

data class DeleteAddressResponse(
    val message: String
)

data class GetAddressResponse(
    val id: String,
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val noInterior: String,
    val colonia: String,
    val cp: Int,
)
