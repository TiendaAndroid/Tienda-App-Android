package com.larc.appandroid.view

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.larc.appandroid.R
import com.larc.appandroid.viewmodel.CarritoVM

@SuppressLint("DefaultLocale")
@Composable
fun TarjetaProductoHome(thisId: String, name: String, price: Double, imgurl: String) {

    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(.8f)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {


            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = name,
                    color = AppColors.GrisOscuro,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(5f)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(13.dp))) {
                    AsyncImage(model = imgurl, contentDescription = null)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Precio: $ ${String.format("%.2f", price)}",
                    color = AppColors.GrisOscuro,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                // BotonDetalleDos(onClick = { /*navController.navigate(Pantallas.RUTA_DETALLE_PRODUCTO+"/${thisId}"+"/${cartId}")*/ })
                // Spacer(modifier = Modifier.height(10.dp))
            }

    }
}

// Botón para mostrar más información
@Composable
fun BotonDetalleDos(onClick: () -> Unit) {
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
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 2.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
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
