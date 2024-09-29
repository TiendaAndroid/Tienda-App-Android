package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.UsuarioVM

@Composable
fun Direcciones(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {

    usuarioVM.getProfile()
    val estadoUsuario = usuarioVM.estadoMiUsuario.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Mis direcciones",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
        estadoUsuario.value.direction?.forEach { direction ->
            item {
                val thisAddressId = direction.id
                val thisCalle = direction.calle
                val thisNoExt = direction.noExterior
                val thisAddress = "$thisCalle, #$thisNoExt..."
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TarjetaDireccion(navController, thisAddressId, thisAddress, usuarioVM.getToken().toString())
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BotonAgregarDireccion(
                    onClick = { navController.navigate(Pantallas.RUTA_NUEVA_DIRECCION+"/${usuarioVM.getToken().toString()}") })
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun BotonAgregarDireccion(onClick: () -> Unit) {
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
                    text = "Agregar nueva dirección",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir dirección",
                    tint = AppColors.RosaZazil
                )
            }
        }
    }
}
