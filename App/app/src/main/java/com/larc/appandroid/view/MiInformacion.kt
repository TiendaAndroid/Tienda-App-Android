package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MiInformacion(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mi información",
            fontSize = 32.sp,
            color = Color(0xFFD5507C),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Nombre: Lucio Mendoza",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Correo: todasbrillamos@gmail.com",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Teléfono: +52 55 1234 5678",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Sección de Dirección
        Text(
            text = "Dirección:",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD5507C),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = "Calle: Carretera Lago de Guadalupe KM 3.5",
            fontSize = 17.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Colonia: San Pedro",
            fontSize = 17.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Ciudad: Estado de México",
            fontSize = 17.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        Text(
            text = "Código Postal: 12345",
            fontSize = 17.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5507C)
            )
        ) {
            Text(text = "Editar información", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MiInformacionPreview() {
    MiInformacion()
}
