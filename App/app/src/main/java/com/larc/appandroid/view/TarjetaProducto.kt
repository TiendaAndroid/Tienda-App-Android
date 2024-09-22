package com.larc.appandroid.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.larc.appandroid.viewmodel.ProductoVM

@Composable
fun TarjetaProducto(thisId: String, navController: NavHostController, text: String, price: Double, imgurl: String) {
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
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = "Precio: $ ",
                        color = AppColors.GrisOscuro,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.weight(4f)
                    )
                    Text(
                        text = price.toString(),
                        color = AppColors.GrisOscuro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(6f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                BotonDetalle(onClick = { navController.navigate(Pantallas.RUTA_DETALLE_PRODUCTO+"/${thisId}") })
                Spacer(modifier = Modifier.height(8.dp))
                BotonAgregar(onClick = {})
                Spacer(modifier = Modifier.height(10.dp))
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
            .fillMaxWidth(.80f)
            .border(2.dp, AppColors.AzulZazil, RoundedCornerShape(20.dp))
            .height(30.dp)
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

@Composable
fun BotonAgregar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.80f)
            .border(2.dp, AppColors.AzulZazil, RoundedCornerShape(20.dp))
            .height(30.dp)
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
