package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.viewmodel.UsuarioVM

@Composable
fun Registrar(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    AppAndroidTheme {
        var nombre by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = "Registro",
                    fontSize = 32.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 26.dp, bottom = 30.dp)
                )
            }
            item {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    placeholder = { Text("Ingresa tu nombre") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {  }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    placeholder = { Text("Ingresa tus apellidos") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {  }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
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
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
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
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .height(50.dp)
                        .background(Color(0xFFD5507C)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
                ) {
                    Text(
                        text = "Registrarse",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
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
                        onClick = { navigateTo(navController, Pantallas.RUTA_SIGN_UP) },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .height(40.dp)
                            .background(Color(0xFFD5507C)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
