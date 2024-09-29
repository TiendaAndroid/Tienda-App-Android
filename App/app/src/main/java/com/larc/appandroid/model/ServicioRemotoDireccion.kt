package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServicioRemotoDireccion {

    // Define the certificate pinning
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Objeto retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Objeto para descargar un dato o servicio
    private val servicio by lazy {
        retrofit.create(DireccionAPI::class.java)
    }

    // Crear una nueva dirección
    suspend fun createAddress(token: String, direccionCompletaRequest: DireccionCompletaRequest): DireccionCompletaResponse? {
        val bearerToken = "Bearer $token"
        return try {
            val response: Response<DireccionCompletaResponse> = servicio.createAddress(bearerToken, direccionCompletaRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}