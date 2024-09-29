package com.larc.appandroid.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Información de las pantallas de la aplicación
 */

object Pantallas {
    const val RUTA_HOME = "home"
    const val RUTA_CUENTA = "cuenta"
    const val RUTA_CARRITO = "carrito"
    const val RUTA_MIS_PEDIDOS = "mis_pedidos"
    const val RUTA_ACERCAC_DE = "acerca_de"
    const val RUTA_SERVICIO_CLIENTE = "servicio_cliente"
    const val RUTA_TESTIMONIOS = "testimonios"
    const val RUTA_FAQS = "faqs"
    const val RUTA_TIENDA_UNO = "tienda_uno"
    const val RUTA_TIENDA_DOS = "tienda_dos"
    const val RUTA_TIENDA_TRES = "tienda_tres"
    const val RUTA_DETALLE_PRODUCTO = "detalle_producto"
    const val RUTA_MI_INFORMACION = "mi_informacion"
    const val RUTA_DIRECCIONES = "direcciones"
    const val RUTA_SIGN_UP = "sign_up"
    const val RUTA_REGISTRAR = "registrar"
    const val RUTA_NUEVA_DIRECCION = "nueva_direccion"
    const val RUTA_ELIMINAR_DIRECCION = "eliminar_direccion"

    val listaPantallas = listOf(
        Pantalla("Home", RUTA_HOME, Icons.Filled.Home),
        Pantalla("Cuenta", RUTA_CUENTA, Icons.Filled.Person),
        Pantalla("Carrito", RUTA_CARRITO, Icons.Filled.ShoppingCart),
    )
}

data class Pantalla(val etiqueta: String, val ruta: String, val icono: ImageVector)
