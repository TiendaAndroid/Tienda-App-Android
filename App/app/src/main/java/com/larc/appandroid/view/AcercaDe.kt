package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.larc.appandroid.R

/**
 * Representa la vista para conocer más sobre el proyecto (Zazil).
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun AcercaDe(navController: NavController, modifier: Modifier = Modifier) {

    val dadata = listOf(
        "¿Quiénes somos?" to "Zazil es una marca comprometida con el bienestar de las mujeres y el cuidado del medio ambiente. Su misión es proporcionar soluciones innovadoras y sostenibles para el período menstrual. ¿Cómo lo hacen? A través de la creación de toallas femeninas reutilizables.",
        "Ahorro a largo plazo" to "Al invertir en Zazil, estás invirtiendo en un producto que dura. Olvídate de compras mensuales; nuestras toallas son una inversión que ahorra dinero con el tiempo.",
        "Oportunidades de emprendimiento" to "Zazil apoya programas que proporcionan oportunidades de emprendimiento para mujeres locales, contribuyendo así al empoderamiento económico en comunidades de todo el mundo.",
        "Únete a nosotros en nuestra misión" to "Cada apoyo, cada voz y cada esfuerzo cuentan. Únete a la Fundación \"Todas Brillamos AC\" en nuestra misión de crear un México más igualitario y libre de pobreza menstrual. ¡Juntos, brillamos más fuerte!"
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
                    text = "Nosotros somos Zazil",
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
            Image(
                painter = painterResource(R.drawable.logozaziltransp),
                contentDescription = "Logo de Todas Brillamos",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 30.dp)
            )
        }
        items(dadata.size) { index ->
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                val (pregunta, respuesta) = dadata[index]
                ContactItem(pregunta, respuesta)
            }
        }
        item {
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Pantallas.RUTA_TODAS_BRILLAMOS) },
                    border = BorderStroke(2.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.GrisOscuro
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Conoce más sobre \"Todas Brillamos AC\"",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = AppColors.GrisOscuro
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
