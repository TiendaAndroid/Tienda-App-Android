package com.larc.appandroid.model

/**
 * Representa la estructura para los items dentro de las órdenes.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class OrderItem(
    val id: String,
    val quantity: Int,
    val addedAt: String,
    val product: Producto,
)
