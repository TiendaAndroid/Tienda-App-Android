package com.larc.appandroid.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.larc.appandroid.viewmodel.PaymentsVM
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

@Composable
fun PaymentScreen(navController: NavHostController, paymentsVM: PaymentsVM, amount: Int, paymentSheet: PaymentSheet) {
    val clientSecret = paymentsVM.clientSecret.collectAsState().value
    val isLoading = paymentsVM.isLoading.collectAsState().value
    val error = paymentsVM.error.collectAsState().value

    // Other details
    val currency = "mxn"
    val tipo: String = "casa"
    val pais: String = "México"
    val municipio: String = "Guadalajara"
    val estado: String = "Jalisco"
    val calle: String = "Av. Morelos"
    val noExterior: String = "123"
    val colonia: String = "Colonia Centro"
    val cp: Int = 44500

    // Load payment intent when screen is shown
    LaunchedEffect(Unit) {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEwZWY5MmI0LWYwZDYtNGI5NS05YmRkLWU4YzRmM2FiMzI5ZCIsImlhdCI6MTcyNzU4OTg1MiwiZXhwIjoxNzMwMTgxODUyfQ.7GZFeontNU-5Fo2RmctH8GuW4mN1d4tNTaI3J1SPt_U" // Replace with actual token
        paymentsVM.createPaymentIntent(token, amount, currency, tipo, pais, municipio, estado, calle, noExterior, colonia, cp)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }

        clientSecret?.let {
            ConfirmPaymentButton(clientSecret = it, paymentSheet = paymentSheet)
        }

        error?.let {
            Text(text = "Error: $it", color = Color.Red)
        }
    }
}

@Composable
fun ConfirmPaymentButton(clientSecret: String, paymentSheet: PaymentSheet) {
    Button(onClick = {
        // Show PaymentSheet to the user
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret = clientSecret,
            configuration = PaymentSheet.Configuration(
                merchantDisplayName = "Your Store Name"
            )
        )
    }) {
        Text(text = "Confirmar Pago")
    }
}

