package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa el listado de productos con un criterio de búsqueda (nombre).
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun TiendaTres(navController: NavHostController, cat: String, productoVM: ProductoVM, usuarioVM: UsuarioVM, carritoVM: CarritoVM, modifier: Modifier = Modifier) {
    val estadoListaBusqueda = productoVM.estadoListaBusqueda.collectAsState()
    val estadoSinResultados = productoVM.estadoSinResultados.collectAsState()
    val estadoTotalResultados = productoVM.estadoTotalResultados.collectAsState()
    val isLoading = productoVM.isLoading.collectAsState().value
    val cartId = usuarioVM.getCartId()

    LaunchedEffect(cat) {
        productoVM.searchProducto(cat)
    }

    // Cambia el mensaje dependiendo de la cantidad de resultados
    val res = if (estadoTotalResultados.value == 1) {
        "resultado"
    } else {
        "resultados"
    }

    // Muestra el disco de carga
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        // Si no se han encontrado productos se muestra un mensaje
    } else {
        if (estadoSinResultados.value) {
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
                Text(text = "Sin resultados para '$cat'",
                    color = AppColors.RosaZazil,)
            }
        } else {

            // Si se han encontrado productos se muestran en una lista
            LazyColumn(modifier = Modifier.fillMaxWidth().background(Color(0xFFFAF8FF))) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${estadoTotalResultados.value} $res para: '$cat'",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = AppColors.RosaZazil,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                estadoListaBusqueda.value.forEach { producto ->
                    item {
                        val thisId = producto.id
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                            TarjetaProducto(
                                thisId,
                                navController,
                                text = producto.name,
                                price = producto.price,
                                imgurl = producto.image[0].url,
                                cartId,
                                carritoVM
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

