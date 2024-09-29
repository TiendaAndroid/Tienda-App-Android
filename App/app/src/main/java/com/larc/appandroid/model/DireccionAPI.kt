package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DireccionAPI {

    @POST("directions")
    suspend fun createAddress(
        @Header("Authorization") token: String,
        @Body direccionCompletaRequest: DireccionCompletaRequest
    ): Response<DireccionCompletaResponse>

    @DELETE("directions/{id}")
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteAddressResponse>

    @GET("directions/{id}")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<GetAddressResponse>

}