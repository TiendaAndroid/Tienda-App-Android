package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Creditos(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(
                    text = "Créditos",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(
                    text = "Esta aplicación fue desarrollada por:\n\n" +
                            "Arturo Barrios Mendoza\n" +
                            "Lucio Arturo Reyes Castillo\n" +
                            "Fidel Alexander Bonilla Montalvo\n" +
                            "Vicente Jesús Ramos Chávez,\n\n" +
                            "Alumnos de 5° semestre de la Ingeniería en Tecnologías computacionales" +
                            "del Instituto Tecnológico y de Estudios Superiores de Monterrey, Campus Estado de México\n\n" +
                            "El proyecto fue concluido en octubre del 2024.\n\n\n" +
                            "Es posible contactar a los desarrolladores en:\n\n" +
                            "arturo_barrios_8@hotmail.com\n" +
                            "lucioarturoreyes@gmail.com\n" +
                            "fbonillamontalvo@gmail.com\n" +
                            "vicentejesusramos@gmail.com",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
