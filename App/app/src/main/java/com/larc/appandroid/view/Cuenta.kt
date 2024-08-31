package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Cuenta(modifier: Modifier = Modifier,
           navigateTo: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Buen día",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Lucio Mendoza",
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 30.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonApachurrable(text = "Mis pedidos", color = Color(0xFFD5507C), onClick = { navigateTo(Pantallas.RUTA_MIS_PEDIDOS) })
            Spacer(modifier = Modifier.width(16.dp))
            BotonApachurrable(text = "Mi información", color = Color(0xFFD5507C), onClick = { navigateTo(Pantallas.RUTA_MI_INFORMACION) })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonApachurrable(text = "Direcciones", color = Color(0xFFD5507C), onClick = { navigateTo(Pantallas.RUTA_DIRECCIONES) })
            Spacer(modifier = Modifier.width(16.dp))
            BotonApachurrable(text = "Servicio al cliente", color = Color(0xFFD5507C), onClick = { navigateTo(Pantallas.RUTA_SERVICIO_CLIENTE) })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonApachurrable(text = "Pagos", color = Color(0xFFD5507C), onClick = { /* Navegar a Configuración */ })
        }
    }
}

@Composable
fun BotonApachurrable(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(150.dp),
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
fun CuentaPreview() {
    Cuenta()
}*/
