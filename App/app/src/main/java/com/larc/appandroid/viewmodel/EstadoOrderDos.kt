package com.larc.appandroid.viewmodel

import com.larc.appandroid.model.OrderItem

/**
 * Representa el estado de una orden.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class EstadoOrderDos(
    val id: String = "",
    val status: String = "",
    val receiptUrl: String = "",
    val paymentId: String = "",
    val tipo: String = "",
    val pais: String = "",
    val municipio: String = "",
    val estado: String = "",
    val calle: String = "",
    val noExterior: String = "",
    val noInterior: String? = "",
    val colonia: String = "",
    val cp: Int = 0,
    val createdAt: String = "",
    val orderItems: List<OrderItem>? = emptyList(),
)
