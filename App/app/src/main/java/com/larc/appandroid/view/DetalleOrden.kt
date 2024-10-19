package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.OrdenVM

/**
 * Representa la vista de los detalles de una orden.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun DetalleOrden(navController: NavHostController, ordenVM: OrdenVM, orderId: String, modifier: Modifier = Modifier) {

    val estadoThisOrder = ordenVM.estadoThisOrder.collectAsState()
    val productosEnLaOrden = ordenVM.shortList.collectAsState()
    ordenVM.getOrders(orderId)

    // Se muestra la dirección para confirmar la eliminación
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8FF)),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Detalles de tu orden",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Id: ${estadoThisOrder.value.id}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(Modifier.fillMaxWidth().padding(top = 6.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Estatus: ${estadoThisOrder.value.status}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Fecha de creación: ${estadoThisOrder.value.createdAt.take(10)}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Dirección de envío: ${estadoThisOrder.value.calle}, " +
                            "#${estadoThisOrder.value.noExterior}, " +
                            "${estadoThisOrder.value.noInterior} " +
                            "${estadoThisOrder.value.colonia}, " +
                            "${estadoThisOrder.value.municipio}, " +
                            "${estadoThisOrder.value.estado}, " +
                            "${estadoThisOrder.value.pais}, " +
                            "${estadoThisOrder.value.cp}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Productos:",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        productosEnLaOrden.value.forEach { prod ->
            item {
                Row(Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)) {
                    Text(
                        text = "${prod.name} x ${prod.quantity}",
                        textAlign = androidx.compose.ui.text.style.TextAlign
                            .Center,
                        color = AppColors.GrisOscuro,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Botón para regresar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(Pantallas.RUTA_MIS_PEDIDOS)
                    },
                    border = BorderStroke(2.dp, Color.LightGray),
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(.9f)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.GrisOscuro
                    )
                ) {
                    Row {
                        Text(
                            text = "Regresar",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }


}