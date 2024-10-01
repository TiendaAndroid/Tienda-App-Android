package com.larc.appandroid.model

/**
 * Representa la estructura para las solicitudes de inicio de sesión y registro.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String,
    val token: String
)
