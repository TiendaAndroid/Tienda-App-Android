package com.larc.appandroid.model

import com.google.gson.annotations.SerializedName

/**
 * Representa la estructura para el carrito de compras.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class Cart(
    val id: String,
    val createdAt: String,
    @SerializedName("cart_items") val cartItems: List<CartItem>
)
