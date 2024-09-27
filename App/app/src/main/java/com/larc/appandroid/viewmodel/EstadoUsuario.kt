package com.larc.appandroid.viewmodel

// CORREGIR CART_ITEM, ORDER_ITEM, ADDED_AT

data class EstadoUsuario(
    val id: String,
    val email: String,
    val name: String,
    val lastName: String,
    val googleId: String?,
    val isActive: Boolean,
    val role: List<String>,
    val direction: List<EstadoDirection>?,
    val cart: EstadoCart,
    val orders: List<String>?,
)

data class EstadoDirection(
    val id: String,
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val noInterior: String?,
    val colonia: String,
    val cp: Int
)

data class EstadoCart(
    val id: String,
    val createdAt: String,
    val cartItems: List<EstadoCartItem>?,
)

data class EstadoCartItem(
    val id: String,
    val name: String,
    val quantity: Int
)

data class EstadoOrder(
    val id: String,
    val status: String,
    val receiptUrl: String,
    val paymentId: String,
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val noInterior: String?,
    val colonia: String,
    val cp: Int,
    val createdAt: String,
    val orderItems: List<EstadoOrderItem>?,
)

data class EstadoOrderItem(
    val id: String,
    val quantity: Int,
    val addedAt: String,
    val product: EstadoProduct,
)

data class EstadoProduct(
    val id: String,
    val price: Double,
    val color: List<String>,
    val name: String,
    val sales: Int,
    val description: String,
    val discount: Double,
    val type: List<String>,
    val stock: Int,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val image: List<EstadoImage>?
)

data class EstadoImage(
    val id: Int,
    val url: String
)
