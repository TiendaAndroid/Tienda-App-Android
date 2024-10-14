package com.larc.appandroid.model

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Representa la estructura para el servicio remoto de pagos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
class ServicioRemotoPayments {

    /**
     * Servicio que interactúa con la API de pagos.
     */
    private val paymentsApi: PaymentsAPI

    /**
     * Inicializa el servicio remoto de pagos.
     */
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fabm.online/zazil_backend/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        paymentsApi = retrofit.create(PaymentsAPI::class.java)
    }

    /**
     * Crea una nueva sesión de pago.
     *
     * @param token El token de autenticación (formato `Bearer`).
     * @param paymentSessionDto Los detalles de la sesión de pago.
     * @return Un objeto `PaymentIntentResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun createPaymentIntent(token: String, paymentSessionDto: PaymentSessionDto): PaymentIntentResponse? {
        return try {
            val response = paymentsApi.createPaymentIntent("Bearer $token", paymentSessionDto)
            if (response.isSuccessful) {
                Log.d("PaymentIntentResponse", response.body().toString())
                response.body()
            } else {
                Log.e("PaymentIntentResponse", "Error: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("PaymentIntentResponse", "Exception: ${e.message}")
            null
        }
    }
}
