package com.larc.appandroid.model

import com.google.gson.annotations.SerializedName

/**
 * Representa la estructura para los items dentro del carrito de compras.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class CartItem(
    val id: String,
    val quantity: Int,
    @SerializedName("added_at") val addedAt: String,
    val product: Producto
)
