package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Representa la estructura para el servicio remoto de direcciones.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class ServicioRemotoDireccion {

    /**
     * Define el *certificate pinning* para asegurar la comunicación con el servidor.
     */
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .build()
    }

    /**
     * Define el cliente OkHttp configurado con *certificate pinning* y tiempos de espera.
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
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Servicio que interactúa con la API de direcciones.
     */
    private val servicio by lazy {
        retrofit.create(DireccionAPI::class.java)
    }

    /**
     * Crea una nueva dirección para el usuario autenticado.
     *
     * @param token El token de autenticación (formato `Bearer`).
     * @param direccionCompletaRequest Los detalles completos de la nueva dirección.
     * @return Un objeto `DireccionCompletaResponse` si la operación fue exitosa, de lo contrario `null`.
     */
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

    /**
     * Elimina una dirección específica del usuario autenticado.
     *
     * @param token El token de autenticación (formato `Bearer`).
     * @param id El ID de la dirección que se desea eliminar.
     * @return Un objeto `DeleteAddressResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun deleteAddress(token: String, id: String): DeleteAddressResponse? {
        val bearerToken = "Bearer $token"
        return try {
            val response: Response<DeleteAddressResponse> = servicio.deleteAddress(bearerToken, id)
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
     * Consulta una dirección específica del usuario autenticado.
     *
     * @param token El token de autenticación (formato `Bearer`).
     * @param id El ID de la dirección que se desea consultar.
     * @return Un objeto `GetAddressResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun getAddress(token: String, id: String): GetAddressResponse? {
        val bearerToken = "Bearer $token"
        return try {
            val response: Response<GetAddressResponse> = servicio.getAddress(bearerToken, id)
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