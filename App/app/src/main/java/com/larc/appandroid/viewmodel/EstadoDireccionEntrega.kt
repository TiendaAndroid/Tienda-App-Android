package com.larc.appandroid.viewmodel

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
