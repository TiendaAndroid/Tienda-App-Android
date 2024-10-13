package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
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

    val ordersList = usuarioVM.ordersList.collectAsState()
    usuarioVM.getProfile()
    usuarioVM.populateOrderList()
    usuarioVM.sortOrderList()

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

        if (ordersList.value.isEmpty()) {
            item {
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Aún no tienes pedidos",
                    Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }

        ordersList.value.forEach { order ->
            item {
                val thisOrderId = order.id
                val thisOrderStatus = order.status
                val thisOrderDate = order.createdAt
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TarjetaOrden(navController, thisOrderId, thisOrderStatus, thisOrderDate)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TarjetaOrden(
    navController: NavHostController,
    thisOrderId: String,
    thisOrderStatus: String,
    thisOrderDate: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(8f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "ID: ",
                        color = AppColors.GrisOscuro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = thisOrderId,
                        color = AppColors.GrisOscuro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = "Fecha: ${thisOrderDate.take(10)}",
                        color = AppColors.GrisOscuro,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Estatus: $thisOrderStatus",
                        color = AppColors.GrisOscuro,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            IconButton(
                onClick = { navController.navigate(Pantallas.RUTA_DETALLE_ORDEN + "/${thisOrderId}") },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Más información",
                    tint = AppColors.RosaZazil
                )
            }
        }
    }
}

