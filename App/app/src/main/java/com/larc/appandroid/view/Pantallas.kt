package com.larc.appandroid.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Información de las pantallas de la aplicación
 */

sealed class Pantallas(
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
) {
    // Objecto de la clase (static)
    companion object {
        val listaPantallas = listOf(Home, Perfil, Carrito)
        const val RUTA_HOME = "Home"
        const val RUTA_PERFIL = "Perfil"
        const val RUTA_CARRITO = "Carrito"
    }
    // Pantallas
    private data object Home: Pantallas(RUTA_HOME, "Home", Icons.Default.Home)
    private data object Perfil: Pantallas(RUTA_PERFIL, "Perfil", Icons.Default.Person)
    private data object Carrito: Pantallas(RUTA_CARRITO, "Carrito", Icons.Default.ShoppingCart)
}