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

    /**
     * Agrega un producto al carrito.
     *
     * Envía una solicitud para agregar un producto al carrito del usuario actual.
     *
     * @param addToCartRequest El objeto que contiene la información del producto a agregar.
     * @return La respuesta del servidor con los detalles del producto agregado.
     */
    @POST("cart/item")
    suspend fun addToCart(
        @Body addToCartRequest: AddToCartRequest
    ): Response<AddToCartResponse>

    /**
     * Consulta el carrito de un usuario por su ID.
     *
     * Obtiene el contenido del carrito del usuario especificado por su ID.
     *
     * @param id El ID del usuario cuyo carrito se va a consultar.
     * @return La respuesta del servidor que contiene los detalles del carrito.
     */
    @GET("cart/user/{id}")
    suspend fun getCart(
        @Path("id") id: String
    ): Response<Cart>

    /**
     * Borra un producto del carrito.
     *
     * Elimina un producto del carrito usando el ID del `cartItem`.
     *
     * @param id El ID del elemento en el carrito que se debe eliminar.
     * @return La respuesta del servidor indicando si la eliminación fue exitosa.
     */
    @DELETE("cart/item/{id}")
    suspend fun deleteItem(
        @Path("id") id: String
    ): Response<Unit>

}