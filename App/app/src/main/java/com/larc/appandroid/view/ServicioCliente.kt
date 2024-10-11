package com.larc.appandroid.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Representa la vista de servicio al cliente.
 * PENDIENTE: CAMBIAR COLORES Y ESTILOS
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun ServicioCliente(modifier: Modifier = Modifier) {

    val dadata = listOf(
        "Llama a:" to "+52 56 2808 3883",
        "Envía un correo a:" to "contacto@fundaciontodasbrillamos.org"
    )

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
                    text = "¿Tienes alguna duda o comentario?",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(dadata.size) { index ->
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                val (pregunta, respuesta) = dadata[index]
                ContactItem(pregunta, respuesta)
            }
        }
    }
}

@Composable
fun ContactItem(pregunta: String, respuesta: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        border = BorderStroke(2.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pregunta,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = AppColors.GrisOscuro
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(
                    text = respuesta,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    modifier = Modifier.padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
