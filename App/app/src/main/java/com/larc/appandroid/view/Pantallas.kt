package com.larc.appandroid.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Información de las pantallas de la aplicación
 */

object Pantallas {
    const val RUTA_HOME = "home"
    const val RUTA_PERFIL = "perfil"
    const val RUTA_CARRITO = "carrito"
    const val RUTA_MIS_PEDIDOS = "mis_pedidos"

    val listaPantallas = listOf(
        Pantalla("Home", RUTA_HOME, Icons.Filled.Home),
        Pantalla("Perfil", RUTA_PERFIL, Icons.Filled.Person),
        Pantalla("Carrito", RUTA_CARRITO, Icons.Filled.ShoppingCart),
    )
}

data class Pantalla(val etiqueta: String, val ruta: String, val icono: ImageVector)

