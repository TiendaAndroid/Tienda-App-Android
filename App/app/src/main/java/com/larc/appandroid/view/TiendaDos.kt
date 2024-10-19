package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa el listado de productos con un criterio de búsqueda (categoría).
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun TiendaDos(navController: NavHostController, cat: String, productoVM: ProductoVM, usuarioVM: UsuarioVM, carritoVM: CarritoVM, modifier: Modifier = Modifier) {
    val estadoListaTodosProductos = productoVM.estadoListaTodosProductos.collectAsState()
    val scrollState = productoVM.estadoScrollTop.collectAsState()
    val listState = rememberLazyListState()
    val pagActual = productoVM.estadoPaginaActual.collectAsState()
    val pagsTotales = productoVM.estadoTotalPaginas.collectAsState()
    val estadoSinResultados = productoVM.estadoSinResultados.collectAsState()
    val isLoading = productoVM.isLoading.collectAsState().value
    val cartId = usuarioVM.getCartId()

    // Se meustra cuando se cargan los productos
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {

        // Si no se han encontrado productos se muestra un mensaje
        if (estadoSinResultados.value) {
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
                Text(text = "No se han encontrado productos.")
            }
        } else {

            // Si se han encontrado productos se muestran en una lista
            LaunchedEffect(scrollState.value) {
                if (scrollState.value) {
                    listState.scrollToItem(0)
                    productoVM.resetScrollTop()
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFAF8FF)),
                state = listState
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Explorando: $cat",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = AppColors.RosaZazil,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                estadoListaTodosProductos.value.forEach { producto ->
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
                                carritoVM,
                                usuarioVM
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        BotonAnterior(productoVM, modifier = Modifier.weight(6f))
                        Spacer(modifier = Modifier.weight(1f))
                        Numerador(
                            pagActual.value + 1,
                            pagsTotales.value,
                            modifier = Modifier.weight(4f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BotonSiguiente(productoVM, modifier = Modifier.weight(6f))
                    }
                }
            }
        }
    }
}

// Numerador de páginas
@Composable
fun Numerador(pagActual: Int, pagsTotales: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(60.dp)
    ) {
        Text(text = "$pagActual/$pagsTotales")
    }
}

// Ir a la página anterior
@Composable
fun BotonAnterior(productoVM: ProductoVM, modifier: Modifier = Modifier) {
    Button(
        onClick = { productoVM.previousPage() },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
        enabled = productoVM.estadoPaginaActual.collectAsState().value > 0
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .widthIn(min = 130.dp, max = 150.dp)
        ) {
            Row {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Anterior",
                )
                Text(
                    text = "Anterior",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

// Ir a la página siguiente
@Composable
fun BotonSiguiente(productoVM: ProductoVM, modifier: Modifier = Modifier) {
    Button(
        onClick = { productoVM.nextPage() },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
        enabled = productoVM.estadoPaginaActual.collectAsState().value < productoVM.estadoTotalPaginas.collectAsState().value-1
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .widthIn(min = 130.dp, max = 150.dp)
        ) {
            Row {
                Text(
                    text = "Siguiente",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Siguiente",
                )
            }
        }
    }
}
