package com.larc.appandroid.model

/**
 * Representa la estructura para la respuesta del backend sobre usuarios.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class UsuarioResponse(
    val id: String,
    val email: String,
    val direction: List<Direction>?,
    val cart: Cart,
    val orders: List<Order>?,
    val token: String
)

/**
 * Representa la estructura para la respuesta del backend sobre usuarios (registro).
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class UsuarioResponseRegister(
    val email: String,
    val name: String,
    val lastName: String,
    val birthDay: String?,
    val phoneNumber: String?,
    val googleId: String?,
    val id: String,
    val isActive: Boolean,
    val role: List<String>?,
    val token: String
)

/**
 * Representa la estructura para la respuesta del backend sobre el perfil de un usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class ProfileResponse(
    val id: String,
    val email: String,
    val name: String,
    val lastName: String,
    val birthDay: String?,
    val phoneNumber: String?,
    val googleId: String?,
    val isActive: Boolean,
    val role: List<String>,
    val direction: List<Direction>?,
    val cart: Cart,
    val orders: List<Order>?
)
