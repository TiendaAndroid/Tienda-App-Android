package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.R

@Composable
fun TiendaDos(cat: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text="Explorando:",
                fontWeight = FontWeight.Normal,
                fontSize = 26.sp,
                modifier = Modifier.padding(start = 16.dp)
                    .weight(4f)
            )
            Text(
                text = cat,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 2.dp, top = 4.dp)
                    .weight(6f)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TarjetaProducto(text = "Producto 1", price = 19.99, painterResource(id = R.drawable.sampletoalla1), onClick = {})
                TarjetaProducto(text = "Producto 2", price = 29.99, painterResource(id = R.drawable.sampletoalla2), onClick = {})
                TarjetaProducto(text = "Producto 3", price = 9.99, painterResource(id = R.drawable.sampletoalla3), onClick = {})
            }
        }
    }
}

@Composable
fun TarjetaProducto(text: String, price: Double, image: Painter, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(.95f)
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
            Column(modifier = Modifier.weight(1f)
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
            .height(40.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD5507C),
            contentColor = Color.White
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
            .height(40.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD5507C),
            contentColor = Color.White
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

///*@Preview(showBackground = true)
//@Composable
//fun TiendaDosPreview() {
//    TiendaDos()
//}*/
