package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Perfil(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Sección de texto
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Buen día",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Lucio Mendoza",
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 50.dp)
            )
        }

        // Primera fila de botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AlargadoButton(text = "Mis Pedidos", color = Color(0xFFF4D0CB), onClick = { /* Navegar a Mis Pedidos */ })
            Spacer(modifier = Modifier.width(16.dp))
            AlargadoButton(text = "Inicio", color = Color(0xFFF4D0CB), onClick = { /* Navegar a Inicio */ })
        }

        // Segunda fila de botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AlargadoButton(text = "Direcciones", color = Color(0xFFF4D0CB), onClick = { /* Navegar a Direcciones */ })
            Spacer(modifier = Modifier.width(16.dp))
            AlargadoButton(text = "Servicio", color = Color(0xFFF4D0CB), onClick = { /* Navegar a Servicio */ })
        }

        // Tercera fila de botones con solo uno
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AlargadoButton(text = "Configuración", color = Color(0xFFF4D0CB), onClick = { /* Navegar a Configuración */ })
        }
    }
}

@Composable
fun AlargadoButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(100.dp), // Ancho y alto de 100dp para hacer el botón cuadrado
        shape = RectangleShape, // Establece la forma como un rectángulo
        colors = ButtonDefaults.buttonColors(
            containerColor = color, // Usa el color hexadecimal proporcionado
            contentColor = Color.Black   // Cambia el color del texto a negro
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilPreview() {
    Perfil()
}
