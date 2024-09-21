package com.larc.appandroid.viewmodel

import com.larc.appandroid.model.Imagenes

data class ProductoActual(
    var id: String = "",
    var price: Double = 0.0,
    var prodName: String = "",
    var description: String = "",
    var image: List<Imagenes> = listOf()
)
