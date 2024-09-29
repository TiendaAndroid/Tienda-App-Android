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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.viewmodel.UsuarioVM

@Composable
fun SignUp(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val estadoToken = usuarioVM.estadoToken.collectAsState()
    val errorLogin = usuarioVM.errorLogin.collectAsState()
    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = "Iniciar sesión",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 30.dp)
            )
        }
        item {
            // Email input field
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                placeholder = { Text("Ingresa tu correo") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
        item {
            // Password input field
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                placeholder = { Text("Ingresa tu contraseña") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        usuarioVM.loginUser(email, password)
                        keyboardController?.hide()
                    }
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
        item {
            // Login button
            Button(
                onClick = {
                    usuarioVM.loginUser(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .height(50.dp)
                    .background(Color(0xFFD5507C)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
            ) {
                Text(
                    text = "Acceder",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        item {
            if (loggedUsuario.value) {
                Text(
                    text = "Login successful! Token: ${estadoToken.value}",
                    color = Color.Green,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
            } else if (!loggedUsuario.value && errorLogin.value) {
                Text(
                    text = "Hubo un error al iniciar sesión. Por favor intenta de nuevo",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                    text = "¿No tienes una cuenta? ",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Button(
                    onClick = { navigateTo(navController, Pantallas.RUTA_REGISTRAR) },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(40.dp)
                        .background(Color(0xFFD5507C)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
                ) {
                    Text(
                        text = "Registrarme",
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

