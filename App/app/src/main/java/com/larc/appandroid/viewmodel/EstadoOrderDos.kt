package com.larc.appandroid.viewmodel

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
    val orderItems: List<EstadoOrderItem>? = emptyList(),
)
