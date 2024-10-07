package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

/**
 * Representa la vista que presenta los pedidos realizados.
 * PENDIENTE: CAMBIAR ESTILOS Y COLORES Y QUE MUESTRE LA INFORMACIÓN
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun MisPedidos(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {

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
                text = "Mis pedidos",
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
    }
}
