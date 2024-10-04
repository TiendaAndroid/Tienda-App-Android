package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.R
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista de la cuenta del usuario. Cambia dependiendo de si está o no logueado.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun Cuenta(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier, navigateTo: (String) -> Unit
) {
    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()

    // Si no está logueado, muestra el formulario de registro
    if (!loggedUsuario.value) {
        SignUp(navController, usuarioVM)
    } else {

        // Si está logueado, muestra la cuenta del usuario
        usuarioVM.getProfile()
        val estadoMiUsuario = usuarioVM.estadoMiUsuario.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAF8FF))
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hola, ${estadoMiUsuario.value.name}",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    //color = AppColors.GrisOscuro,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Mis pedidos",
                        onClick = { navigateTo(Pantallas.RUTA_MIS_PEDIDOS) },
                        imagen = R.drawable.applbag.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrable(
                        text = "Mi información",
                        onClick = { navigateTo(Pantallas.RUTA_MI_INFORMACION) },
                        imagen = R.drawable.applperson.toString())
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Direcciones",
                        onClick = { navigateTo(Pantallas.RUTA_DIRECCIONES) },
                        imagen = R.drawable.appllocation.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrable(
                        text = "Servicio al cliente",
                        onClick = { navigateTo(Pantallas.RUTA_SERVICIO_CLIENTE) },
                        imagen = R.drawable.applhead.toString())
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Pagos",
                        onClick = { /* Navegar a Configuración */ },
                        imagen = R.drawable.applpay.toString())
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}

// Botón con imagen y texto para cada servicio en cuenta
@Composable
fun BotonApachurrable(text: String, onClick: () -> Unit, imagen: String) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor =  Color(0xFFFAF8FF),
            contentColor = AppColors.GrisOscuro
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = imagen.toInt()),
                    contentDescription = "Imagen del recurso",
                    modifier = Modifier.fillMaxHeight())
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.width(180.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    //fontWeight = FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}
