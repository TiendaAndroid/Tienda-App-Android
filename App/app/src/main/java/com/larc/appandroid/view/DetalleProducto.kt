package com.larc.appandroid.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.larc.appandroid.viewmodel.ProductoVM

@Composable
fun DetalleProducto(id: String, productoVM: ProductoVM, modifier: Modifier = Modifier) {
    val prodActual = productoVM.estadoProductoActual.collectAsState()
    val estadoSinResultIndiv = productoVM.estadoSinResultIndiv.collectAsState()
    val isLoading = productoVM.isLoading.collectAsState().value
    LaunchedEffect(id) {
        productoVM.getProductoPorId(id)
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        if (estadoSinResultIndiv.value) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Ha habido un error al cargar el producto.")
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
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
                            text = prodActual.value?.price.toString(),
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
                        BotonAgregarCarrito()
                    }
                }

            }
        }
    }
}

@Composable
fun BotonAgregarCarrito() {
    Button(
        onClick = {  },
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
