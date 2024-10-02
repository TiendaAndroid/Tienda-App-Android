package com.larc.appandroid.model

/**
 * Representa la estructura para el carrito de compras.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class Cart(
    val id: String,
    val createdAt: String,
    val cartItems: List<CartItem>
)
