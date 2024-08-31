package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.larc.appandroid.ui.theme.AppAndroidTheme

@Composable
fun Registrar() {
    AppAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registrarse",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            var email by remember { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                placeholder = { Text("Ingresa tu correo") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onNext = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                placeholder = { Text("Ingresa tu contraseña") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onNext = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            var nombre by remember { mutableStateOf("") }
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre Completo") },
                placeholder = { Text("Ingresa tu nombre completo") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onNext = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            var celular by remember { mutableStateOf("") }
            TextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text("Número de Celular") },
                placeholder = { Text("Ingresa tu número de celular") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                    onNext = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            var direccion by remember { mutableStateOf("") }
            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección Completa") },
                placeholder = { Text("Ingresa tu dirección completa") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Botón cuadrado para registrarse
            Button(
                onClick = { /* Manejar acción de registro */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFD5507C)), // Fondo con color hexadecimal
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White, // Texto blanco del botón
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // Espacio entre el botón de registrarse y el nuevo contenido

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Ya tienes una cuenta? ",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(30.dp)
                        .background(Color(0xFFD5507C)), // Fondo con color hexadecimal
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
                ) {
                    Text(
                        text = "INICIAR SESIÓN",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistarPreview() {
    Registrar()
}
