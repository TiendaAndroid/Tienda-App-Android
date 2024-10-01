package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Representa las llamadas a la API relacionadas con productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

interface ProductoAPI {

    // Consultar productos (de 5 en 5)
    @GET("products")
    suspend fun getProductos(
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    // Consultar productos por categoría (de 5 en 5)
    @GET("products/tipo/{categoria}")
    suspend fun getProductosPorCategoria(
        @Path("categoria") categoria: String,
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    // Consultar producto por id
    @GET("products/{id}")
    suspend fun getProductoPorId(
        @Path("id") id: String
    ): Response<Producto>

    // Consultar productos por palabra clave
    @GET("products/search/{palabra}")
    suspend fun searchProducts(
        @Path("palabra") palabra: String
    ): Response<ProductoResponse>
}