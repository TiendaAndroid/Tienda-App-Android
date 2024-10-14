package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Representa las llamadas a la API relacionadas con pagos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
interface PaymentsAPI {
    /**
     * Crea una sesión de pago para Android.
     *
     * Envía una solicitud al backend para crear un PaymentIntent y devuelve el `clientSecret` necesario
     * para confirmar el pago en la app.
     *
     * @param token El token de autorización en formato `Bearer`.
     * @param paymentSessionDto Los detalles de la sesión de pago como monto y moneda.
     * @return La respuesta con el `clientSecret` si la operación fue exitosa.
     */
    @POST("payments/create-payment-session-android")
    suspend fun createPaymentIntent(
        @Header("Authorization") token: String,
        @Body paymentSessionDto: PaymentSessionDto
    ): Response<PaymentIntentResponse>
}

/**
 * Representa la respuesta de la creación de un PaymentIntent, contiene el clientSecret.
 */
data class PaymentIntentResponse(val clientSecret: String)

/**
 * DTO para enviar los detalles de la sesión de pago al backend.
 * Incluye el monto y la moneda.
 */
data class PaymentSessionDto(
    val currency: String,
    val tipo: String,
    val pais: String,
    val municipio: String,
    val estado: String,
    val calle: String,
    val noExterior: String,
    val noInterior: String,
    val colonia: String,
    val cp: Int
)
