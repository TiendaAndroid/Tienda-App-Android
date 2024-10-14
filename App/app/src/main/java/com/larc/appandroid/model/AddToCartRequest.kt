package com.larc.appandroid.model

/**
 * Representa la estructura para la solicitud de agregar un producto al carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class AddToCartRequest(
    val cart: String,
    val product: String,
    val quantity: Int,
)
