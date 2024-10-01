package com.larc.appandroid.model

/**
 * Representa la estructura para el usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class Usuario(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
    val googleId: Int,
    val isActive: Boolean,
    val role: List<String>
)
