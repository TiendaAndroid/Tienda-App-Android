@file:Suppress("DEPRECATION")

package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista para el registro de un usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registrar(navController: NavHostController, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    AppAndroidTheme {
        var nombre by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var codigo by remember { mutableStateOf("") }
        var fechaNacimiento by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        val estadoCorreoEnviado = usuarioVM.mailEnviado.collectAsState()
        val errorSendMail = usuarioVM.errorSendMail.collectAsState()
        val errorRegistro = usuarioVM.errorRegister.collectAsState()
        val registroExitoso = usuarioVM.registroExitoso.collectAsState()
        val keyboardController = LocalSoftwareKeyboardController.current

        var day by remember { mutableStateOf("") }
        var month by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }

        if (estadoCorreoEnviado.value) {

            // Mensaje de registro exitoso
            if (registroExitoso.value) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFAF8FF)),
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Text(
                            text = "¡Registro exitoso!",
                            textAlign = TextAlign.Center,
                            color = AppColors.RosaZazil,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        Row(modifier = modifier
                            .fillMaxWidth()
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
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = AppColors.RosaZazil
                                    )
                                }
                            }
                        }
                    }
                }
            } else {

                // Solicita el código enviaro al correo
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFAF8FF)),
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Ingrese el código enviado\na su correo",
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = AppColors.RosaZazil,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Mensaje de error si el código no es correcto
                    if (errorRegistro.value) {
                        item {
                            Text(
                                text = "Hubo un problema. Por favor verifique que su código sea correcto.",
                                color = Color.Red,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 32.dp, end = 32.dp, bottom = 16.dp)
                            )
                        }
                    }
                    item {
                        TextField(
                            value = codigo,
                            onValueChange = { codigo = it },
                            label = { Text("Código") },
                            placeholder = { Text("Ingresa tu código") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Text
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = AppColors.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                    // Botón para terminar el registro
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (day.length == 1) {
                                day = "0$day"
                            }
                            if (month.length == 1) {
                                month = "0$month"
                            }
                            fechaNacimiento = "$day/$month/$year"
                            Button(
                                onClick = {
                                    usuarioVM.registerUSer(
                                        email,
                                        nombre,
                                        apellidos,
                                        password,
                                        codigo,
                                        fechaNacimiento,
                                        telefono
                                    )
                                },
                                border = BorderStroke(2.dp, Color.LightGray),
                                modifier = Modifier
                                    .height(80.dp)
                                    .fillMaxWidth(.9f)
                                    .clip(RoundedCornerShape(20.dp))
                                    .padding(16.dp),
                                shape = RoundedCornerShape(20),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.White,
                                    contentColor = AppColors.GrisOscuro
                                )
                            ) {
                                Text(
                                    text = "Terminar registro",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        } else {

            // Solicita el registro
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFAF8FF)),
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Registro",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = AppColors.RosaZazil,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Mensaje de error si la primera parte del registro no es exitosa
                if (errorSendMail.value) {
                    item {
                        Text(
                            text = "Hubo un error. Por favor revise sus datos. " +
                                    "La contraseña debe tener 6 o más caracteres, debe contar con al menos un número, una letra mayúscula, una letra minúscula y un carácter especial. " +
                                    "La contraseña debe comenzar con una letra mayúscula. " +
                                    "Verifique que su correo no tenga espacios en blanco al inicio ni al final.",
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
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
                            onNext = { }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
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
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
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
                            onNext = { }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                item {
                    Text(
                        text = "La contraseña debe tener 6 o más caracteres, debe contar con al menos un número, una letra mayúscula, una letra minúscula y un carácter especial. " +
                                "Debe comenzar con una letra mayúscula.",
                        color = AppColors.GrisOscuro,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, end = 32.dp, bottom = 16.dp)
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
                            onNext = { }
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Fecha de nacimiento:",
                            color = AppColors.GrisOscuro,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.weight(1f)
                                .padding(horizontal = 16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextField(
                            value = day,
                            onValueChange = {
                                if (it.length <= 2) day = it
                            },
                            label = { Text("Día") },
                            placeholder = { Text("dd") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                                .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = AppColors.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        TextField(
                            value = month,
                            onValueChange = {
                                if (it.length <= 2) month = it
                            },
                            label = { Text("Mes") },
                            placeholder = { Text("mm") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                                .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = AppColors.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        TextField(
                            value = year,
                            onValueChange = {
                                if (it.length <= 4) year = it
                            },
                            label = { Text("Año") },
                            placeholder = { Text("aaaa") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .weight(2f)
                                .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = AppColors.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }

                item {
                    TextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = { Text("Teléfono") },
                        placeholder = { Text("Ingresa tu teléfono") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                usuarioVM.registerSendMail(
                                    email,
                                    nombre,
                                    apellidos,
                                    password
                                )
                                keyboardController?.hide()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                // Primer botón para registrarse
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                usuarioVM.registerSendMail(
                                    email,
                                    nombre,
                                    apellidos,
                                    password
                                )
                            },
                            border = BorderStroke(2.dp, Color.LightGray),
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth(.9f)
                                .clip(RoundedCornerShape(20.dp))
                                .padding(16.dp),
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.White,
                                contentColor = AppColors.GrisOscuro
                            )
                        ) {
                            Text(
                                text = "Registrarse",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Opción para iniciar sesión si ya tiene una cuenta
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
                            border = BorderStroke(2.dp, Color.LightGray),
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .clip(RoundedCornerShape(20.dp))
                                .padding(8.dp),
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.White,
                                contentColor = AppColors.GrisOscuro
                            )
                        ) {
                            Text(
                                text = "Iniciar sesión",
                                fontWeight = FontWeight.Normal,
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
}
