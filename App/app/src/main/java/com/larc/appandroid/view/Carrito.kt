package com.larc.appandroid.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.R // Asegúrate de tener el ícono en el recurso drawable
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista del carrito del usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun Carrito(carritoVM: CarritoVM, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    val carrito = carritoVM.carritoNoRepeat.collectAsState()
    //val carrito = carritoVM.productosCarrito.collectAsState()
    val userId = usuarioVM.estadoMiUsuario.collectAsState()
    carritoVM.getCart(userId.value.id)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Mi carrito",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

        carrito.value.forEach { item ->
            item {
                val thisName = item.name
                val thisQuantity = item.quantity
                val thisPrice = 10.0 //item.price
                val thisImage = "a" //item.image
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProductoItem(producto = thisName, cantidad = thisQuantity, price = thisPrice, onAdd = {}, onRemove = {})
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ProductoItem(producto = "Producto 1", cantidad = 2, price = 10.0, onAdd = {}, onRemove = {})
            }
        }
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ProductoItem(producto = "Producto 1", cantidad = 2, price = 10.0, onAdd = {}, onRemove = {})
            }
        }
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ProductoItem(producto = "Producto 1", cantidad = 2, price = 10.0, onAdd = {}, onRemove = {})
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BotonPagar(
                    onClick = {})
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductoItem(
    producto: String,
    cantidad: Int,
    price: Double,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = producto)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${String.format("%.2f", price)}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = onRemove,
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.size(30.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.RosaZazil
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
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.RosaZazil
                    )
                ) {
                    Text(text = "+")
                }
            }
        }
    }
}


// Botón para proceder al pago
@Composable
fun BotonPagar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(.8f)
        ) {
            Row {
                Text(
                    text = "Proceder al pago",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Pagar",
                    tint = AppColors.RosaZazil
                )
            }
        }
    }
}
