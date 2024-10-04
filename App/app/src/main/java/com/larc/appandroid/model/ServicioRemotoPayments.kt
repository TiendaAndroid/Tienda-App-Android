package com.larc.appandroid.model

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicioRemotoPayments {

    private val paymentsApi: PaymentsAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fabm.online/zazil_backend/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        paymentsApi = retrofit.create(PaymentsAPI::class.java)
    }

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
