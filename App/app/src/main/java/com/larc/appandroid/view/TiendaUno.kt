package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.ProductoVM

@Composable
fun TiendaUno(navController: NavHostController, productoVM: ProductoVM, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Explora nuestro catálogo",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 16.dp),
            color = AppColors.RosaZazil
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Selecciona una categoría:",
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp),
            color = AppColors.RosaZazil
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row {
                    BotonApachurrableDos(
                        text = "Todos los productos",
                        onClick = { productoVM.getAllProductos(0); productoVM.filterOff(); productoVM.resetPagActual(); navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Todos los productos") })
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrableDos(
                        text = "Toallas regulares",
                        onClick = { productoVM.getProductosByCategory("Regular", 0); productoVM.filterOn(); productoVM.resetPagActual(); navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas regulares") })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    BotonApachurrableDos(
                        text = "Toallas nocturnas",
                        onClick = { productoVM.getProductosByCategory("Nocturna", 0); productoVM.filterOn(); productoVM.resetPagActual(); navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas nocturnas") })
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrableDos(text = "Toallas teen",
                        onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas teen") })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    BotonApachurrableDos(
                        text = "Pantiprotectores",
                        onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Pantiprotectores") })
                    Spacer(modifier = Modifier.width(16.dp))
                    BotonApachurrableDos(text = "Kits",
                        onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Kits") })
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BotonApachurrableDos(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun TiendaUnoPreview() {
    TiendaUno()
}*/
