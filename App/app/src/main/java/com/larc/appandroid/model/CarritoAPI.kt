package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Representa las llamadas a la API relacionadas con carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

interface CarritoAPI {

    // Agregar producto al carrito
    @POST("cart/item")
    suspend fun addToCart(
        @Body addToCartRequest: AddToCartRequest
    ): Response<AddToCartResponse>

    // Consultar carrito por id
    @GET("cart/user/{id}")
    suspend fun getCart(
        @Path("id") id: String
    ): Response<Cart>

    // Borrar un producto del carrito
    @DELETE("cart/item/{id}")
    suspend fun deleteItem(
        @Path("id") id: String
    ): Response<Unit>

}