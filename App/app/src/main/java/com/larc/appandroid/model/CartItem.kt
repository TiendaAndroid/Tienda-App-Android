package com.larc.appandroid.model

/**
 * Representa la estructura para los items dentro del carrito de compras.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class CartItem(
    val id: String,
    val name: String,
    val quantity: Int
)
