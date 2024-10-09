package com.larc.appandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.larc.appandroid.view.AppPrincipal
import com.larc.appandroid.view.Pantallas
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

/**
 * Representa la actividad principal de la aplicación.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class MainActivity : ComponentActivity() {
    private lateinit var paymentSheet: PaymentSheet
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
        setContent {
            navController = rememberNavController()
            AppPrincipal(navController, paymentSheet)
        }
    }
    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Completed -> {
                navController.navigate(Pantallas.RUTA_MIS_PEDIDOS)
            }
            is PaymentSheetResult.Canceled -> {
            }
            is PaymentSheetResult.Failed -> {
            }
        }
    }
}
