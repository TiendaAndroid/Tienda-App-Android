package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Representa la vista de servicio al cliente.
 * PENDIENTE: CAMBIAR COLORES Y ESTILOS
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun ServicioCliente(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Servicio al Cliente",
            fontSize = 32.sp,
            color = Color(0xFFD5507C),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Cualquier comentario o sugerencia",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5507C)
            )
        ) {
            Text(text = "Llamar", color = Color.White)
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5507C)
            )
        ) {
            Text(text = "Enviar un correo", color = Color.White)
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5507C)
            )
        ) {
            Text(text = "Chatear", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicioClientePreview() {
    ServicioCliente()
}
