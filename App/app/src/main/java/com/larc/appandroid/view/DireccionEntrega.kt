package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.PaymentsVM
import com.larc.appandroid.viewmodel.UsuarioVM

/**
 * Representa la vista con el listado de direcciones del usuario para seleccionar la dirección de entrega.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun DireccionEntrega(navController: NavHostController, usuarioVM: UsuarioVM, paymentsVM: PaymentsVM, total: Double, modifier: Modifier = Modifier) {

    usuarioVM.getProfile()
    val estadoUsuario = usuarioVM.estadoMiUsuario.collectAsState()
    var selectedAddressId by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Selecciona la dirección\nde entrega:",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
        estadoUsuario.value.direction?.forEach { direction ->
            item {
                val thisAddressId = direction.id
                val thisCalle = direction.calle
                val thisNoExt = direction.noExterior
                val thisAddress = "$thisCalle, #$thisNoExt..."
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TarjetaDireccionDos(
                        navController,
                        thisAddressId,
                        thisAddress,
                        selectedAddressId = selectedAddressId,
                        onSelect = { selectedAddressId = thisAddressId
                        paymentsVM.updateEstadoDireccionEntrega(direction.tipo,
                            direction.pais,
                            direction.municipio,
                            direction.estado,
                            direction.calle,
                            direction.noExterior,
                            direction.noInterior,
                            direction.colonia,
                            direction.cp) } )
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BotonContinuarDireccion(
                    onClick = {
                        if (selectedAddressId != null) {
                            navController.navigate(Pantallas.RUTA_PAYMENT_SCREEN + "/${total}")
                        }
                    })
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun TarjetaDireccionDos(navController: NavHostController,
                        thisAddressId: String,
                        thisAdress: String,
                        selectedAddressId: String?,
                        onSelect: () -> Unit,
                        modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(.95f)
            .height(80.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row(Modifier.padding(10.dp)) {
            Text(
                text = thisAdress,
                color = AppColors.GrisOscuro,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
                    .weight(6f)
                    .padding(top = 4.dp, start = 5.dp)
            )
            IconButton(
                onClick = onSelect, // Call onSelect when the button is pressed
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = if (selectedAddressId == thisAddressId) Icons.Default.CheckCircle else Icons.Default.Check, // Check icon if selected, default otherwise
                    contentDescription = if (selectedAddressId == thisAddressId) "Dirección seleccionada" else "Seleccionar dirección",
                    tint = if (selectedAddressId == thisAddressId) AppColors.RosaZazil else Color.Gray // Change color based on selection
                )
            }
        }
    }
}

@Composable
fun BotonContinuarDireccion(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro,
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(.8f)
        ) {
            Row {
                Text(
                    text = "Continuar con el pago",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}
