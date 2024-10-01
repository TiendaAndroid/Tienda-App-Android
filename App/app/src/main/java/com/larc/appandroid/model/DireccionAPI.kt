package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Representa las llamadas a la API relacionadas con direcciones.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

interface DireccionAPI {

    // Crear nueva dirección
    @POST("directions")
    suspend fun createAddress(
        @Header("Authorization") token: String,
        @Body direccionCompletaRequest: DireccionCompletaRequest
    ): Response<DireccionCompletaResponse>

    // Borrar dirección
    @DELETE("directions/{id}")
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteAddressResponse>

    // Consultar dirección
    @GET("directions/{id}")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<GetAddressResponse>

}