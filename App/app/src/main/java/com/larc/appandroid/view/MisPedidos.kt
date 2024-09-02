package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MisPedidos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis Pedidos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD5507C),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        PedidoItem(
            pedidoId = "12345",
            fecha = "01/09/2024",
            estado = "En tránsito"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PedidoItem(
            pedidoId = "67890",
            fecha = "25/08/2024",
            estado = "Entregado"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PedidoItem(
            pedidoId = "76542",
            fecha = "30/08/2024",
            estado = "Entregado"
        )
    }
}

@Composable
fun PedidoItem(pedidoId: String, fecha: String, estado: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4D0CB)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Pedido ID: $pedidoId", fontSize = 20.sp, color = Color.Black)
            Text(text = "Fecha: $fecha", fontSize = 18.sp, color = Color.Gray)
            Text(text = "Estado: $estado", fontSize = 18.sp, color = Color.Gray)

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD5507C)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(40.dp)
            ) {
                Text(text = "Rastrear Pedido", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MisPedidosPreview() {
    MisPedidos()
}
