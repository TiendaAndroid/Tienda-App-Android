package com.larc.appandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.PaymentsVM
import com.larc.appandroid.viewmodel.UsuarioVM
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

@SuppressLint("DefaultLocale")
@Composable
fun PaymentScreen(navController: NavHostController, paymentsVM: PaymentsVM, usuarioVM: UsuarioVM, amount: Double, paymentSheet: PaymentSheet, modifier: Modifier = Modifier) {
    val clientSecret = paymentsVM.clientSecret.collectAsState().value
    val isLoading = paymentsVM.isLoading.collectAsState().value
    val error = paymentsVM.error.collectAsState().value
    val selectedAddress = paymentsVM.estadoDireccionEntrega.collectAsState().value

    // Other details
    val currency = "mxn"
    /*
    val tipo: String = "casa"
    val pais: String = "México"
    val municipio: String = "Guadalajara"
    val estado: String = "Jalisco"
    val calle: String = "Av. Morelos"
    val noExterior: String = "123"
    val colonia: String = "Colonia Centro"
    val cp: Int = 44500
     */

    // Load payment intent when screen is shown
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
            Text(text = "Total: $ ${String.format("%.2f", amount)}")
            ConfirmPaymentButton(clientSecret = it, paymentSheet = paymentSheet)
        }

        error?.let {
            Text(text = "Error: $it", color = Color.Red)
        }
    }
}

@Composable
fun ConfirmPaymentButton(clientSecret: String, paymentSheet: PaymentSheet, modifier: Modifier = Modifier) {
    Button(onClick = {
        // Show PaymentSheet to the user
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

