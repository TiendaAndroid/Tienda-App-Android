package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.R
import com.larc.appandroid.viewmodel.UsuarioVM

@Composable
fun Carta(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {

    val personas = usuarioVM.personas.collectAsState()
    val kilogramos = usuarioVM.kilogramos.collectAsState()
    val ahorro = usuarioVM.ahorro.collectAsState()
    val usuario = usuarioVM.estadoMiUsuario.collectAsState()
    usuarioVM.calcularAyuda()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.carta),
                    contentDescription = "Banner de carta",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "Nuestra carta para ti",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Apreciable ${usuario.value.name},",
                    textAlign = TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Queremos agradecerte, pues con tus compras has ayudado a",
                    textAlign = TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(3f))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = personas.value.toString(),
                        textAlign = TextAlign.Center,
                        color = AppColors.RosaZazil,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    var pluralSing = "mujer"
                    if (personas.value > 1) {
                        pluralSing = "mujeres"
                    }
                    Text(
                        text = pluralSing,
                        textAlign = TextAlign.Center,
                        color = AppColors.GrisOscuro,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.weight(3f))
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "a tener una menstruación digna.\nAdemás, gracias a tu decisión de adquirir toallas femeninas reutilizables, dejarás de generar aproximadamente",
                    textAlign = TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = kilogramos.value.toString(),
                        textAlign = TextAlign.Center,
                        color = AppColors.RosaZazil,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "kilogramos",
                        textAlign = TextAlign.Center,
                        color = AppColors.GrisOscuro,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "de basura",
                        textAlign = TextAlign.Center,
                        color = AppColors.GrisOscuro,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "y ahorrarás alrededor de",
                    textAlign = TextAlign.Center,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "$ ${ahorro.value}",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "¡Gracias por ayudarnos a hacer de éste un mundo mejor, un ciclo a la vez!",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Pantallas.RUTA_CALCULOS) },
                    border = BorderStroke(2.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.White
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "¿Quieres saber más sobre estos cálculos?",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = AppColors.RosaZazil
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}
