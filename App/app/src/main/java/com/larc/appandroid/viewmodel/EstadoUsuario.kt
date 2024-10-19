package com.larc.appandroid.viewmodel

import com.larc.appandroid.model.Cart
import com.larc.appandroid.model.Direction
import com.larc.appandroid.model.Order

/**
 * Representa algunas estructuras para los estados. Todos parten del usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class EstadoUsuario(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val lastName: String = "",
    val birthDay: String? = null,
    val phoneNumber: String? = null,
    val googleId: String? = null,
    val isActive: Boolean = false,
    val role: List<String> = emptyList(),
    val direction: List<Direction>? = null,
    val cart: Cart? = null,
    val orders: List<Order>? = null
)
