package com.larc.appandroid.model

/**
 * Representa la estructura para un producto.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

data class Producto(
    val id: String,
    val price: Double,
    val color: List<String>,
    val name: String,
    val sales: Int,
    val description: String,
    val discount: Double,
    val stock: Int,
    val isActive: Boolean,
    val image: List<Imagenes>,
    val type: List<String>,
    val valor: Int
)
