package com.larc.appandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.larc.appandroid.view.AppPrincipal
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

/**
 * Representa la actividad principal de la aplicación.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class MainActivity : ComponentActivity() {
    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar PaymentSheet
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        setContent {
            // Pass the paymentSheet to your composables
            AppPrincipal(paymentSheet)
        }
    }

    // Handle PaymentSheet results here
    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Completed -> {
                // Payment succeeded
            }
            is PaymentSheetResult.Canceled -> {
                // Payment canceled
            }
            is PaymentSheetResult.Failed -> {
                // Payment failed
            }
        }
    }
}
