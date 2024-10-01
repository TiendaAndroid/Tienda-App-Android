package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.R // Asegúrate de tener el ícono en el recurso drawable

/**
 * Representa la vista del carrito del usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun Carrito() {
    AppAndroidTheme {
        var carrito by remember { mutableStateOf(mutableMapOf<String, Int>()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Título "Carrito"
            Text(
                text = "Carrito",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            // Lista de productos
            val productos = listOf(
                "Producto 1",
                "Producto 2",
                "Producto 3",
                "Producto 4"
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(productos.size) { index ->
                    val producto = productos[index]
                    ProductoItem(
                        producto = producto,
                        cantidad = carrito[producto] ?: 0,
                        onAdd = {
                            carrito = carrito.toMutableMap().apply {
                                this[producto] = (this[producto] ?: 0) + 1
                            }
                        },
                        onRemove = {
                            carrito = carrito.toMutableMap().apply {
                                val newQuantity = (this[producto] ?: 0) - 1
                                if (newQuantity > 0) {
                                    this[producto] = newQuantity
                                } else {
                                    this.remove(producto)
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Botón "Pagar"
            Button(
                onClick = { /* Manejar el proceso de pago */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFD5507C)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
            ) {
                Text(
                    text = "Pagar",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ProductoItem(producto: String, cantidad: Int, onAdd: () -> Unit, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF1F1F1)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto (icono de ejemplo)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Nombre del producto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = producto)
                Spacer(modifier = Modifier.height(8.dp))
                // Controles de cantidad
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = onRemove,
                        contentPadding = PaddingValues(4.dp),
                        modifier = Modifier.size(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD5507C),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "-")
                    }

                    Text(
                        text = "$cantidad",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Button(
                        onClick = onAdd,
                        contentPadding = PaddingValues(4.dp),
                        modifier = Modifier.size(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD5507C),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "+")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarritoPreview() {
    Carrito()
}
