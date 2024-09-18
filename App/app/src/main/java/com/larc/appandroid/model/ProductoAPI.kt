package com.larc.appandroid.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductoAPI {
    @GET("products")
    suspend fun getProductos(
        @Query("limit") limit: Int = 1,
        @Query("offset") offset: Int
    ): ProductoResponse
}