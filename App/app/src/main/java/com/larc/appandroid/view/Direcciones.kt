package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Direcciones(modifier: Modifier = Modifier) {
    var direccion by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var codigoPostal by remember { mutableStateOf("") }
    var colonia by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingresar Dirección",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Calle y número") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = colonia,
            onValueChange = { ciudad = it },
            label = { Text("Colonia") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = ciudad,
            onValueChange = { ciudad = it },
            label = { Text("Ciudad") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text("Estado") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = codigoPostal,
            onValueChange = { codigoPostal = it },
            label = { Text("Código Postal") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5507C)
            )
        ) {
            Text(
                text = "Guardar",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DireccionesPreview() {
    Direcciones()
}
