package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServicioRemotoOrders {

    /**
     * Define el *certificate pinning* para asegurar la comunicación con el servidor.
     */
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            //.add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .add("fabm.online", "sha256/BTvVjAWnPX4FDA4NB0n6OlshgFbmV/5RAV8M8BFG/o8=")
            .build()
    }

    /**
     * Define el cliente OkHttp configurado con el *certificate pinning* y tiempos de espera.
     */
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Objeto Retrofit configurado para la base de datos de la tienda.
     * Utiliza el cliente OkHttp previamente configurado.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            //.baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .baseUrl("https://fabm.online/zazil_backend/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Servicio que interactúa con la API de direcciones.
     */
    private val servicio by lazy {
        retrofit.create(OrdersAPI::class.java)
    }

    suspend fun getOrder(id: String): Response<Order>? {
        return try {
            val response: Response<Order> = servicio.getOrder(id)
            if (response.isSuccessful) {
                response
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}
