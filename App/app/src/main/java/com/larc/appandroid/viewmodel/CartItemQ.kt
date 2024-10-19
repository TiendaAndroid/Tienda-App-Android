package com.larc.appandroid.viewmodel

/**
 * Representa un elemento del carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class CartItemQ(
    val productId: String,
    val quantity: Int,
    val name: String,
    val price: Double,
    val image: String,
    val totalPrice: Double = price * quantity
)
