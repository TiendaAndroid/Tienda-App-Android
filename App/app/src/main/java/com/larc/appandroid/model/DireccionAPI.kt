package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DireccionAPI {

    @POST("directions")
    suspend fun createAddress(
        @Header("Authorization") token: String,
        @Body direccionCompletaRequest: DireccionCompletaRequest
    ): Response<DireccionCompletaResponse>
}