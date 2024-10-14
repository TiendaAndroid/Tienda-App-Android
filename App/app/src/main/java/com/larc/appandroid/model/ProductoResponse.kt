package com.larc.appandroid.model

/**
 * Representa la estructura para la respuesta del backend sobre productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class ProductoResponse(
    val limit: Int,
    val offset: Int,
    val partialResults: Int,
    val totalResults: Int,
    val data: List<Producto>
)
