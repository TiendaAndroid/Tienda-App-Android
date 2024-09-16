package com.larc.appandroid.model

data class Producto(
    val id: String,
    val price: Double,
    val color: List<String>,
    val name: String,
    val description: String,
    val discount: Double,
    val material: List<String>,
    val size: List<String>,
    val stock: Int,
    val isActive: Boolean,
    val image: List<Imagenes>,
    val category: String
)
