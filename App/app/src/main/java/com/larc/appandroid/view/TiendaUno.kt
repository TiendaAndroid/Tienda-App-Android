package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista de las diferentes categorías de productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun TiendaUno(navController: NavHostController, productoVM: ProductoVM, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text="Explora nuestro catálogo",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontWeight = FontWeight.Normal,
                //fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                //modifier = Modifier.padding(start = 16.dp),
                color = AppColors.RosaZazil
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text="Selecciona una categoría:",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                //modifier = Modifier.padding(start = 16.dp),
                color = AppColors.RosaZazil
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                BotonApachurrableDos(
                    text = "Ver todos los productos",
                    onClick = { productoVM.getAllProductos(0)
                        productoVM.filterOff()
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Todos los productos") },
                    imagen = R.drawable.appleye.toString())
                Spacer(modifier = Modifier.width(16.dp))
                BotonApachurrableDos(
                    text = "Toallas regulares",
                    onClick = { productoVM.getProductosByCategory("Toalla Regular", 0)
                        productoVM.filterOn()
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas regulares") },
                    imagen = R.drawable.appltoalla.toString())
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                BotonApachurrableDos(
                    text = "Toallas nocturnas",
                    onClick = { productoVM.getProductosByCategory("Toalla Nocturna", 0)
                        productoVM.filterOn()
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas nocturnas") },
                    imagen = R.drawable.applmoon.toString())
                Spacer(modifier = Modifier.width(16.dp))
                BotonApachurrableDos(text = "Toallas teen",
                    onClick = { productoVM.getProductosByCategory("Toalla Teen", 0)
                        productoVM.filterOn()
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas teen") },
                    imagen = R.drawable.applstar.toString())
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                BotonApachurrableDos(
                    text = "Pantiprotectores",
                    onClick = { productoVM.getProductosByCategory("Pantiprotectores Diarios", 0)
                        productoVM.filterOn()
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Pantiprotectores") },
                    imagen = R.drawable.applshield.toString())
                Spacer(modifier = Modifier.width(16.dp))
                BotonApachurrableDos(text = "Kits",
                    onClick = { productoVM.getProductosByCategory("Kits", 0)
                        productoVM.filterOn();
                        productoVM.resetPagActual()
                        navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Kits") },
                    imagen = R.drawable.applbox.toString())
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Botón con imagen y texto
@Composable
fun BotonApachurrableDos(text: String, onClick: () -> Unit, imagen: String) {
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
            //containerColor = AppColors.CremaZazil,
            //contentColor = AppColors.RosaZazil
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
                    //fontWeight = FontWeight.Bold,
                    fontWeight = FontWeight.Normal,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}
