package com.larc.appandroid.model

import android.util.Log
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

    /**
     * Define el *certificate pinning* para la comunicación segura con el servidor.
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
     * Servicio que interactúa con la API del carrito.
     */
    private val servicio by lazy {
        retrofit.create(CarritoAPI::class.java)
    }

    /**
     * Agrega un producto al carrito.
     *
     * Envía una solicitud a la API para agregar el producto especificado al carrito del usuario.
     *
     * @param addToCartRequest El objeto que contiene los datos del producto que se agregará al carrito.
     * @return Un objeto `AddToCartResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun addToCart(addToCartRequest: AddToCartRequest): AddToCartResponse? {
        return try {
            val response = servicio.addToCart(addToCartRequest)
            if (response.isSuccessful) {
                Log.d("Status", "Success")
                response.body()
            } else {
                Log.d("Status", "Not success :( ")
                null
            }
        } catch (e: Exception) {
            Log.d("Status", "$addToCartRequest")
            Log.d("Status", "Aquí está el error")
            Log.d("Status", "Error, $e")
            null
        }
    }

    /**
     * Consulta el carrito del usuario por su ID.
     *
     * @param id El ID del usuario cuyo carrito se va a consultar.
     * @return Un objeto `Cart` si la operación fue exitosa, de lo contrario `null`.
     */
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

    /**
     * Elimina un producto del carrito por su ID.
     *
     * @param id El ID del `cartItem` que se debe eliminar.
     * @return `Unit?` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun deleteItem(id: String): Unit? {
        return try {
            val response = servicio.deleteItem(id)
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