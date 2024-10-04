package com.larc.appandroid.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

/**
 * Representa un elemento para mostrar cada dirección del usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun TarjetaDireccion(navController: NavHostController,
                     thisAddressId: String,
                     thisAdress: String,
                     token: String,
                     modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(.95f)
            .height(80.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row(Modifier.padding(10.dp)) {
            Text(
                text = thisAdress,
                color = AppColors.GrisOscuro,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
                    .weight(6f)
                    .padding(top = 4.dp, start = 5.dp)
            )
            /*
            IconButton(
                onClick = {},
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar dirección",
                    tint = AppColors.RosaZazil
                )
            }
             */
            IconButton(
                onClick = { navController.navigate(Pantallas.RUTA_ELIMINAR_DIRECCION + "/${token}" + "/${thisAddressId}") },
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar dirección",
                    tint = AppColors.RosaZazil
                )
            }
        }
    }
}
