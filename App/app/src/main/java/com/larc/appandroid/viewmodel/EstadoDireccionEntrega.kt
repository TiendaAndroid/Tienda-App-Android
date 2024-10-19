package com.larc.appandroid.viewmodel

/**
 * Representa el estado de una dirección de entrega.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class EstadoDireccionEntrega(
    val tipo: String = "",
    val pais: String = "",
    val municipio: String = "",
    val estado: String = "",
    val calle: String = "",
    val noExterior: String = "",
    val noInterior: String? = "",
    val colonia: String = "",
    val cp: Int = 0
)
