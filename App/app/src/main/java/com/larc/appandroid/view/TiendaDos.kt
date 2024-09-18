package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.larc.appandroid.R
import com.larc.appandroid.viewmodel.ProductoVM
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TiendaDos(cat: String, productoVM: ProductoVM, modifier: Modifier = Modifier) {
    val estadoListaTodosProductos = productoVM.estadoListaTodosProductos.collectAsState()
    val scrollState = productoVM.estadoScrollTop.collectAsState()
    val listState = rememberLazyListState()
    val pagActual = productoVM.estadoPaginaActual.collectAsState()
    val pagsTotales = productoVM.estadoTotalPaginas.collectAsState()
    LaunchedEffect(scrollState.value) {
        if (scrollState.value) {
            listState.scrollToItem(0)
            productoVM.resetScrollTop()
        }
    }
    Column(modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text="Explorando:",
                fontWeight = FontWeight.Normal,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(4f)
            )
            Text(
                text = cat,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 2.dp, top = 4.dp)
                    .weight(6f)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            estadoListaTodosProductos.value.forEach { producto ->
                item {
                    TarjetaProducto(text = producto.name, price = producto.price, imgurl = producto.image[0].url, onClick = {})
                }
            }
            item {
                TarjetaProducto2(text = "Producto 1", price = 19.99, painterResource(id = R.drawable.sampletoalla1), onClick = {})
                TarjetaProducto2(text = "Producto 2", price = 29.99, painterResource(id = R.drawable.sampletoalla2), onClick = {})
                TarjetaProducto2(text = "Producto 3", price = 9.99, painterResource(id = R.drawable.sampletoalla3), onClick = {})
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    BotonAnterior(productoVM, modifier = Modifier.weight(6f))
                    Spacer(modifier = Modifier.weight(1f))
                    Numerador(pagActual.value+1, pagsTotales.value, modifier = Modifier.weight(4f))
                    Spacer(modifier = Modifier.weight(1f))
                    BotonSiguiente(productoVM, modifier = Modifier.weight(6f))
                }
            }
        }
    }
}

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

@Composable
fun TarjetaProducto(text: String, price: Double, imgurl: String, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(.95f)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .height(170.dp)
                    .clip(RoundedCornerShape(13.dp))) {
                AsyncImage(model = imgurl, contentDescription = null)
            }
            Column(modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp, start = 15.dp)) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Precio: $ ",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.weight(4f)
                    )
                    Text(
                        text = price.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(6f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                BotonDetalle(onClick = {})
                Spacer(modifier = Modifier.height(8.dp))
                BotonAgregar(onClick = {})
            }
        }
    }
}

@Composable
fun TarjetaProducto2(text: String, price: Double, image: Painter, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(.95f)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row {
            Image(
                painter = image,
                contentDescription = "Logo de Todas Brillamos",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(13.dp))
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp, start = 15.dp)) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Precio: $ ",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.weight(4f)
                    )
                    Text(
                        text = price.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(6f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                BotonDetalle(onClick = {})
                Spacer(modifier = Modifier.height(8.dp))
                BotonAgregar(onClick = {})
            }
        }
    }
}

@Composable
fun BotonDetalle(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.75f)
            .border(2.dp, AppColors.AzulZazil, RoundedCornerShape(20.dp))
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.AzulZazil,
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(
                    text = "Ver detalle",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 1.dp, top = 2.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Más información",
                )
            }
        }
    }
}

@Composable
fun BotonAgregar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.75f)
            .border(2.dp, AppColors.AzulZazil, RoundedCornerShape(20.dp))
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.AzulZazil,
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(
                    text = "Añadir",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 1.dp, top = 2.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Agregar al carrito",
                )
            }
        }
    }
}

@Composable
fun BotonAnterior(productoVM: ProductoVM, modifier: Modifier = Modifier) {
    Button(
        onClick = { productoVM.previousPage() },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .height(60.dp)
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
                .widthIn(min = 150.dp, max = 200.dp)
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

@Composable
fun BotonSiguiente(productoVM: ProductoVM, modifier: Modifier = Modifier) {
    Button(
        onClick = { productoVM.nextPage() },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .height(60.dp)
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
                .widthIn(min = 150.dp, max = 200.dp)
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
