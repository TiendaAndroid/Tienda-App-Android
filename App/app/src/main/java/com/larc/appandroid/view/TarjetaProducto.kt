package com.larc.appandroid.view

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa un elemento para mostrar cada producto.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@SuppressLint("DefaultLocale")
@Composable
fun TarjetaProducto(thisId: String, navController: NavHostController, text: String, price: Double, imgurl: String, cartId: String, carritoVM: CarritoVM, usuarioVM: UsuarioVM) {

    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()

    val errorAgregar = carritoVM.errorAgregarProducto.collectAsState()
    val productoAgregado = carritoVM.productoAgregado.collectAsState()
    val messageShowed = carritoVM.messageShowed.collectAsState()

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
                    .weight(5f)
                    .padding(10.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(13.dp))) {
                AsyncImage(model = imgurl, contentDescription = null)
            }
            Column(modifier = Modifier
                .weight(6f)
                .padding(top = 15.dp, start = 15.dp)) {
                Text(
                    text = text,
                    color = AppColors.GrisOscuro,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Precio: $ ${String.format("%.2f", price)}",
                    color = AppColors.GrisOscuro,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                BotonDetalle(onClick = { navController.navigate(Pantallas.RUTA_DETALLE_PRODUCTO+"/${thisId}"+"/${cartId}") })
                Spacer(modifier = Modifier.height(8.dp))
                BotonAgregar(onClick = { carritoVM.addToCart(cartId, thisId) }, enabled = loggedUsuario.value)
                Spacer(modifier = Modifier.height(10.dp))
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
            onDismiss = {
                carritoVM.setMessageShowed()
                carritoVM.resetErrores()
                showDialog.value = false },
            message = message.value,
            onButtonClick = {
                carritoVM.setMessageShowed()
                carritoVM.resetErrores()
            }
        )
    }
}

// Botón para mostrar más información
@Composable
fun BotonDetalle(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.80f)
            .border(2.dp, AppColors.AzulZazil, RoundedCornerShape(20.dp))
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(100),
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
                    fontSize = 14.sp,
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

// Botón para agregar al carrito
@Composable
fun BotonAgregar(onClick: () -> Unit, enabled: Boolean) {
    var borderColor = AppColors.AzulZazil
    if (!enabled) {
        borderColor = Color.Gray
    }
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.80f)
            .border(2.dp, borderColor, RoundedCornerShape(20.dp))
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.AzulZazil
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(
                    text = "Añadir",
                    fontSize = 14.sp,
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
