package com.larc.appandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TiendaUno(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Explora nuestro catálogo",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Selecciona una categoría:",
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                BotonApachurrableDos(
                    text = "Ejemplo de categoría",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Ejemplo de categoría") })
                BotonApachurrableDos(
                    text = "Toallas regulares",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas regulares") })
                BotonApachurrableDos(
                    text = "Toallas nocturnas",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas nocturnas") })
                BotonApachurrableDos(text = "Toallas teen",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Toallas teen") })
                BotonApachurrableDos(
                    text = "Pantiprotectores",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Pantiprotectores") })
                BotonApachurrableDos(text = "Kits",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Kits") })
                BotonApachurrableDos(
                    text = "Todos los productos",
                    color = Color(0xFFD5507C),
                    onClick = { navController.navigate(Pantallas.RUTA_TIENDA_DOS+"/Todos los productos") })
            }
        }
    }
}

@Composable
fun BotonApachurrableDos(text: String, color: Color, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(.85f)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun TiendaUnoPreview() {
    TiendaUno()
}*/
