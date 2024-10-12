package com.larc.appandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.larc.appandroid.R
import com.larc.appandroid.model.NetworkManager
import com.larc.appandroid.viewmodel.NetworkManagerVM
import com.larc.appandroid.viewmodel.ProductoVM

/**
 * Representa la vista de inicio de la aplicación.
 * PENDIENTE: HACER TODDO LO QUE LLEVA
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@SuppressLint("DefaultLocale")
@Composable
fun Home(navController: NavController, productoVM: ProductoVM, modifier: Modifier = Modifier) {

    val currentIndex = productoVM.currentHomeIndex.collectAsState()
    val totalItems = productoVM.totalHomeItems.collectAsState()
    val currentHome = productoVM.currentHome.collectAsState()

    productoVM.getProductosHome()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = "Logo de Todas Brillamos",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(text = "Algunos de nuestros productos:",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth())
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(300.dp),
                verticalAlignment = Alignment.CenterVertically) {
                BotonAnteriorDos(onclic = {
                    productoVM.prevHome()
                }, modifier = Modifier.weight(2f))
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize(.8f)
                        .clip(RoundedCornerShape(20.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
                ) {


                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        currentHome.value?.name?.let {
                            Text(
                                text = it,
                                color = AppColors.GrisOscuro,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(5f)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(13.dp))) {
                            AsyncImage(model = currentHome.value?.image, contentDescription = null)
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Precio: $ ${String.format("%.2f", currentHome.value?.price)}",
                            color = AppColors.GrisOscuro,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        // BotonDetalleDos(onClick = { /*navController.navigate(Pantallas.RUTA_DETALLE_PRODUCTO+"/${thisId}"+"/${cartId}")*/ })
                        // Spacer(modifier = Modifier.height(10.dp))
                    }

                }
                /*
                Box(modifier = Modifier.weight(20f)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))) {
                    Image(
                        painter = painterResource(R.drawable.logotodasbrillamos),
                        contentDescription = "Logo de Todas Brillamos",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
                 */
                Spacer(modifier = Modifier.weight(1f))
                BotonSiguienteDos(onclic = {
                    productoVM.nextHome()
                }, modifier = Modifier.weight(2f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 0 until totalItems.value) {
                    IndicatorDot(isSelected = i == currentIndex.value)
                }
            }
        }
        /*
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 0 until totalItems) {
                    IndicatorDot(isSelected = i == currentIndex)
                }
            }
        }
         */
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 32.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Pantallas.RUTA_TIENDA_UNO) },
                    border = BorderStroke(2.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.White
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "¡Explora el catálogo!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = AppColors.RosaZazil
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun BotonAnteriorDos(onclic: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onclic,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, CircleShape)
            .size(40.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
        //enabled = productoVM.estadoPaginaActual.collectAsState().value > 0
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Anterior",
                )
            }
        }
    }
}

// Ir a la página siguiente
@Composable
fun BotonSiguienteDos(onclic: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onclic,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .border(2.dp, Color.LightGray, CircleShape)
            .size(40.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
        //enabled = productoVM.estadoPaginaActual.collectAsState().value < productoVM.estadoTotalPaginas.collectAsState().value-1
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Siguiente",
                )
            }
        }
    }
}

@Composable
fun IndicatorDot(isSelected: Boolean) {
    val color = if (isSelected) AppColors.RosaZazil else Color.LightGray
    Box(
        modifier = Modifier
            //.size(12.dp)
            .width(16.dp)
            .height(8.dp)
            .padding(horizontal = 4.dp)
            .clip(CircleShape)
            .background(color)
    )
}
