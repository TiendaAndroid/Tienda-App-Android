package com.larc.appandroid.viewmodel

/**
 * Representa un producto en el home.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
data class HomeProduct(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val image: String = "",
)
