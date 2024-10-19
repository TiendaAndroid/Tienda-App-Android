package com.larc.appandroid.view

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Representa la vista de servicio al cliente.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun ServicioCliente(modifier: Modifier = Modifier) {

    val context = LocalContext.current

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
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize(.95f)
                        .height(100.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
                ) {
                    Row(Modifier.padding(10.dp)) {
                        Text(
                            text = "Llama a:\n+52 56 2808 3883",
                            color = AppColors.GrisOscuro,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth()
                                .weight(6f)
                                .padding(top = 4.dp, start = 5.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    intent.data = Uri.parse("tel:+525628083883")
                                    context.startActivity(intent)
                                }
                        )
                    }
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize(.95f)
                        .height(100.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
                ) {
                    Row(Modifier.padding(10.dp)) {
                        Text(
                            text = "Envía un correo a:\ncontacto@fundaciontodasbrillamos.org",
                            color = AppColors.GrisOscuro,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth()
                                .weight(6f)
                                .padding(top = 4.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:contacto@fundaciontodasbrillamos.org")
                                    }
                                    context.startActivity(intent)
                                }
                        )
                    }
                }
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
