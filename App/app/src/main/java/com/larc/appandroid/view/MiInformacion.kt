package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista que muestra la información personal del usuario.
 * PENDIENTE: CAMBIAR COLORES, ESTILOS Y QUE JALE LA INFORMACIÓN
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun MiInformacion(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {

    val usuarioDatos = usuarioVM.estadoMiUsuario.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Mi información",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Nombre: ${usuarioDatos.value.name} ${usuarioDatos.value.lastName}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = "Correo: ${usuarioDatos.value.email}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(Pantallas.RUTA_CUENTA)
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
