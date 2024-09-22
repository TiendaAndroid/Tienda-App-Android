package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductoAPI {

    @GET("products")
    suspend fun getProductos(
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    @GET("products/tipo/{categoria}")
    suspend fun getProductosPorCategoria(
        @Path("categoria") categoria: String,
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    @GET("products/{id}")
    suspend fun getProductoPorId(
        @Path("id") id: String
    ): Response<Producto>

    @GET("products/search/{palabra}")
    suspend fun searchProducts(
        @Path("palabra") palabra: String
    ): Response<ProductoResponse>
}