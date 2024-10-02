package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Representa la estructura para el servicio remoto de carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class ServicioRemotoCarrito {

    // Define the certificate pinning
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .build()
    }

    // Define the OkHttpClient with the certificate pinning
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
        retrofit.create(CarritoAPI::class.java)
    }

    // Agregar producto al carrito
    suspend fun addToCart(addToCartRequest: AddToCartRequest): AddToCartResponse? {
        return try {
            val response = servicio.addToCart(addToCartRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // Consultar carrito por id
    suspend fun getCart(id: String): Cart? {
        return try {
            val response = servicio.getCart(id)
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