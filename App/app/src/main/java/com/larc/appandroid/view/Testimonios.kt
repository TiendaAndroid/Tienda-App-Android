package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Representa la vista de los testimonios en la aplicación.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun Testimonios(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Testimonios",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD5507C),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TestimonioItem(
            nombre = "Rosa María",
            comentario = "Excelente servicio, muy satisfecha con mi compra."
        )

        Spacer(modifier = Modifier.height(16.dp))

        TestimonioItem(
            nombre = "Sofía Inés García",
            comentario = "El producto llegó rápido y en perfectas condiciones. ¡Muy recomendado!"
        )

        Spacer(modifier = Modifier.height(16.dp))

        TestimonioItem(
            nombre = "Josefina Mota",
            comentario = "El producto llegó rápido y en perfectas condiciones. ¡Muy recomendado!"
        )

        Spacer(modifier = Modifier.height(16.dp))

        TestimonioItem(
            nombre = "Ana Pérez ",
            comentario = "El producto llegó rápido y en perfectas condiciones. ¡Muy recomendado!"
        )
    }
}

@Composable
fun TestimonioItem(nombre: String, comentario: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4D0CB)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Nombre: $nombre", fontSize = 20.sp, color = Color.Black)
            Text(text = comentario, fontSize = 18.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestimoniosPreview() {
    Testimonios()
}
