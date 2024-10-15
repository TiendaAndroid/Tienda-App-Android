package com.larc.appandroid.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista con la información de un producto seleccionado.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@SuppressLint("DefaultLocale")
@Composable
fun DetalleProducto(id: String, cartId: String, productoVM: ProductoVM, carritoVM: CarritoVM, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()
    val prodActual = productoVM.estadoProductoActual.collectAsState()
    val estadoSinResultIndiv = productoVM.estadoSinResultIndiv.collectAsState()
    val isLoading = productoVM.isLoading.collectAsState().value
    val errorAgregar = carritoVM.errorAgregarProducto.collectAsState()
    val productoAgregado = carritoVM.productoAgregado.collectAsState()
    val messageShowed = carritoVM.messageShowed.collectAsState()

    // Cargar el producto cuando cambia el ID
    LaunchedEffect(id) {
        productoVM.getProductoPorId(id)
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        if (estadoSinResultIndiv.value) {
            Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFFAF8FF)), contentAlignment = Alignment.Center) {
                Text(text = "Ha habido un error al cargar el producto.")
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth().background(Color(0xFFFAF8FF))
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = prodActual.value?.name ?: "Producto no encontrado",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontSize = 25.sp,
                        //modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        color = AppColors.RosaZazil
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .height(300.dp)
                                .clip(RoundedCornerShape(13.dp))
                        ) {
                            AsyncImage(
                                model = prodActual.value?.image?.get(0)?.url,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                        Text(
                            text = "$",
                            fontSize = 20.sp,
                            color = AppColors.RosaZazil,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = String.format("%.2f", prodActual.value?.price),
                            fontSize = 20.sp,
                            color = AppColors.RosaZazil,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Características:",
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = prodActual.value?.description ?: "Producto no encontrado",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        BotonAgregarCarrito(onClick = { carritoVM.addToCart(cartId, id) })
                    }
                }

            }
        }
    }
    if (!messageShowed.value) {
        val showDialog = remember { mutableStateOf(false) }
        val message = remember { mutableStateOf("") }

        if (productoAgregado.value && !errorAgregar.value) {
            message.value = "Producto agregado al carrito"
            showDialog.value = true
            carritoVM.resetErrores()
        } else if (errorAgregar.value) {
            if (!loggedUsuario.value) {
                message.value = "Error: ingresa a tu cuenta"
            } else {
                message.value = "El producto no se agregó"
            }
            showDialog.value = true
            carritoVM.resetErrores()
        }

        ShowProductDialog(
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false },
            message = message.value,
            onButtonClick = {
                carritoVM.setMessageShowed()
                carritoVM.resetErrores()
            }
        )
    }
}


// Botón para agregar un producto al carrito de compras
@Composable
fun BotonAgregarCarrito(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(80.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.AzulZazil,
            contentColor = AppColors.White,
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(.8f)
        ) {
            Row {
                Text(
                    text = "Añadir al carrito",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Siguiente",
                )
            }
        }
    }
}
