package com.larc.appandroid.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.UsuarioVM

@Composable
fun Cuenta(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier, navigateTo: (String) -> Unit
) {
    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()

    if (!loggedUsuario.value) {
        SignUp(navController, usuarioVM)
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(
                        text = "Hola, ",
                        color = AppColors.GrisOscuro,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(4f)
                    )
                    Text(
                        text = "Lucio",
                        color = AppColors.GrisOscuro,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 2.dp, top = 2.dp)
                            .weight(6f)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Mis pedidos",
                        color = Color(0xFFD5507C),
                        onClick = { navigateTo(Pantallas.RUTA_MIS_PEDIDOS) })
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrable(
                        text = "Mi información",
                        color = Color(0xFFD5507C),
                        onClick = { navigateTo(Pantallas.RUTA_MI_INFORMACION) })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Direcciones",
                        color = Color(0xFFD5507C),
                        onClick = { navigateTo(Pantallas.RUTA_DIRECCIONES) })
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrable(
                        text = "Servicio al cliente",
                        color = Color(0xFFD5507C),
                        onClick = { navigateTo(Pantallas.RUTA_SERVICIO_CLIENTE) })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BotonApachurrable(
                        text = "Pagos",
                        color = Color(0xFFD5507C),
                        onClick = { /* Navegar a Configuración */ })
                }
            }
        }

    }
}

@Composable
fun BotonApachurrable(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.CremaZazil,
            contentColor = AppColors.RosaZazil
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.width(180.dp)
        ) {
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}
