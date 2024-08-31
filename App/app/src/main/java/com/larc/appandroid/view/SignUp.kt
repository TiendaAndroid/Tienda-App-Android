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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.ui.theme.AppAndroidTheme

@Composable
fun SignUp() {
    AppAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            var email by remember { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                placeholder = { Text("Ingresa tu correo") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {  }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Botón cuadrado para acceder
            Button(
                onClick = { /* Manejar acción de registro */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFD5507C)), // Fondo con color hexadecimal
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
            ) {
                Text(
                    text = "Acceder",
                    color = Color.White, // Texto blanco del botón
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // Espacio entre el botón de acceder y el nuevo contenido

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
                    onClick = {  },
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(30.dp)
                        .background(Color(0xFFD5507C)), // Fondo con color hexadecimal
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5507C))
                ) {
                    Text(
                        text = "REGISTRARSE",
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
fun SignUpPreview() {
    SignUp()
}
