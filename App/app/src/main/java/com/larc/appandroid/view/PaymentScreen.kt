package com.larc.appandroid.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.PaymentsVM
import com.larc.appandroid.viewmodel.UsuarioVM
import com.stripe.android.paymentsheet.PaymentSheet

/**
 * Representa la vista para el pago.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@SuppressLint("DefaultLocale")
@Composable
fun PaymentScreen(navController: NavHostController, paymentsVM: PaymentsVM, usuarioVM: UsuarioVM, carritoVM: CarritoVM, amount: Double, paymentSheet: PaymentSheet, modifier: Modifier = Modifier) {
    val clientSecret = paymentsVM.clientSecret.collectAsState().value
    val isLoading = paymentsVM.isLoading.collectAsState().value
    val error = paymentsVM.error.collectAsState().value
    val selectedAddress = paymentsVM.estadoDireccionEntrega.collectAsState().value
    val carritoRepeat = carritoVM.productosCarrito.collectAsState()
    val numProductos = carritoRepeat.value.size

    val currency = "mxn"

    LaunchedEffect(Unit) {
        val token = usuarioVM.getToken()
        paymentsVM.createPaymentIntent(token,
            currency,
            selectedAddress.tipo,
            selectedAddress.pais,
            selectedAddress.municipio,
            selectedAddress.estado,
            selectedAddress.calle,
            selectedAddress.noExterior,
            selectedAddress.colonia,
            selectedAddress.cp)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = AppColors.White)
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }

        clientSecret?.let {
            Column(modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Dirección de entrega:")
                Text(text = "${selectedAddress.calle}, ${selectedAddress.noExterior}")
                Text(text = "${selectedAddress.colonia}, ${selectedAddress.municipio}")
                Text(text = "${selectedAddress.estado}, ${selectedAddress.cp}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Productos: $numProductos")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Total (con IVA 16%): $ ${String.format("%.2f", amount)}")
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmPaymentButton(clientSecret = it, paymentSheet = paymentSheet, navController = navController)
            }
        }

        error?.let {
            Text(text = "Error: $it", color = Color.Red)
        }
    }
}

@Composable
fun ConfirmPaymentButton(clientSecret: String, paymentSheet: PaymentSheet, navController: NavHostController, modifier: Modifier = Modifier) {
    Button(onClick = {
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret = clientSecret,
            configuration = PaymentSheet.Configuration(
                merchantDisplayName = "Your Store Name"
            )
        )
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.AzulZazil,
            contentColor = AppColors.White,
        ),
        ) {
        Text(text = "Pagar")
    }
}

