package com.larc.appandroid.model

/**
 * Representa la estructura para la respuesta de agregar un producto al carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class AddToCartResponse(
    val quantity: Int,
    val cart: Cart,
    val product: Producto,
    val id: String,
    val addedAt: String,
)
