package com.larc.appandroid.model

/**
 * Representa la estructura para las solicitudes de inicio de sesión y registro.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class LoginRequest(
    val email: String,
    val password: String
)

/**
 * Representa la estructura para la solicitud de registro para enviar el mail.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class SignupRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String
)

/**
 * Representa la estructura para la solicitud de registro.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class RegisterRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String,
    val token: String,
    val birthDay: String,
    val phoneNumber: String
)
