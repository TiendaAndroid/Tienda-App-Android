package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.DireccionVM

/**
 * Representa la vista para agregar una nueva dirección.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun NuevaDireccion(navController: NavHostController, token: String, direccionVM: DireccionVM, modifier: Modifier = Modifier) {

    var errorGuardar = direccionVM.errorNuevaDireccion.collectAsState()
    var direccionCreada = direccionVM.direccionCreada.collectAsState()

    var tipo by remember { mutableStateOf("casa") }
    var pais by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var calle by remember { mutableStateOf("") }
    var noExterior by remember { mutableStateOf("") }
    var noInterior by remember { mutableStateOf("") }
    var colonia by remember { mutableStateOf("") }
    var cp by remember { mutableIntStateOf(0) }

    val keyboardController = LocalSoftwareKeyboardController.current

    // Si la dirección se creó correctamente, mostrar un mensaje de éxito
    if (direccionCreada.value) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAF8FF)),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = "¡Dirección guardada\ncon éxito!",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    fontSize = 26.sp,
                    color = AppColors.GrisOscuro,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate(Pantallas.RUTA_DIRECCIONES)
                                  direccionVM.resetErrores() },
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
                            text = "Volver a 'mis direcciones'",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    } else {

        // Formulario para agregar una nueva dirección
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAF8FF)),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Agregar nueva dirección",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                TextField(
                    value = pais,
                    onValueChange = { pais = it },
                    label = { Text("País*") },
                    placeholder = { Text("Ingresa tu país") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text("Estado*") },
                    placeholder = { Text("Ingresa tu estado") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = municipio,
                    onValueChange = { municipio = it },
                    label = { Text("Municipio*") },
                    placeholder = { Text("Ingresa tu municipio") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = colonia,
                    onValueChange = { colonia = it },
                    label = { Text("Colonia*") },
                    placeholder = { Text("Ingresa tu colonia") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = calle,
                    onValueChange = { calle = it },
                    label = { Text("Calle*") },
                    placeholder = { Text("Ingresa tu calle") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            item {
                Row {
                    TextField(
                        value = noExterior,
                        onValueChange = { noExterior = it },
                        label = { Text("No. exterior*") },
                        placeholder = { Text("Ingresa tu número exterior") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                    TextField(
                        value = noInterior,
                        onValueChange = { noInterior = it },
                        label = { Text("No. Interior") },
                        placeholder = { Text("Ingresa tu número interior") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
            item {
                TextField(
                    value = cp.toString(),
                    onValueChange = { newValue -> cp = newValue.toIntOrNull() ?: 0 },
                    label = { Text("Código postal*") },
                    placeholder = { Text("Ingresa tu código postal") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            direccionVM.createAddress(
                                    token,
                                    tipo,
                                    pais,
                                    municipio,
                                    estado,
                                    calle,
                                    noExterior,
                                    noInterior,
                                    colonia,
                                    cp
                                )
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }

            // Letrero de error si hubo un error al guardar la dirección
            item {
                if (errorGuardar.value) {
                    Text(
                        text = "Hubo un error al guardar la dirección. Por favor intenta de nuevo.",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    )
                }
            }

            // Botón para guardar la dirección
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            direccionVM.createAddress(
                                token,
                                tipo,
                                pais,
                                municipio,
                                estado,
                                calle,
                                noExterior,
                                noInterior,
                                colonia,
                                cp
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
                        Row {
                            Text(
                                text = "Guardar dirección",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Añadir dirección",
                                tint = AppColors.RosaZazil
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
