package com.larc.appandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.ProductoVM

@Composable
fun TiendaTres(navController: NavHostController, cat: String, productoVM: ProductoVM, modifier: Modifier = Modifier) {
    productoVM.busquedaProducto(cat)
    val estadoListaBusqueda = productoVM.estadoListaBusqueda.collectAsState()
    val estadoSinResultados = productoVM.estadoSinResultados.collectAsState()
    val searchComplete = productoVM.searchComplete.collectAsState()
    if (!searchComplete.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Buscando...")
        }
    } else {
        if (estadoSinResultados.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Sin resultados para '${cat}'")
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxWidth(),
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(
                            text="Resultados para:",
                            color = AppColors.GrisOscuro,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(4f)
                        )
                        Text(
                            text = cat,
                            color = AppColors.GrisOscuro,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .weight(6f)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
                estadoListaBusqueda.value.forEach { producto ->
                    item {
                        val thisId = producto.id
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                            TarjetaProducto(thisId, navController, text = producto.name, price = producto.price, imgurl = producto.image[0].url)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
