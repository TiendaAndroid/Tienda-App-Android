package com.larc.appandroid.model

/**
 * Representa la estructura para la solicitud de dirección.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class DireccionCompletaRequest(
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val noInterior: String?,
    val colonia: String,
    val cp: Int
)
