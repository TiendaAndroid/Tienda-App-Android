package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz que define las operaciones relacionadas con las órdenes en la API remota.
 * Proporciona métodos para interactuar con los recursos de órdenes, como obtener detalles de una orden específica.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
interface OrdersAPI {

    /**
     * Obtiene los detalles de una orden específica a través de su ID.
     *
     * @param id El identificador único de la orden que se quiere consultar.
     * @return Una respuesta HTTP que contiene la información de la orden solicitada.
     *         Si la solicitud es exitosa, la respuesta incluirá un objeto de tipo [Order].
     */
    @GET("orders/{id}")
    suspend fun getOrder(
        @Path("id") id: String
    ): Response<Order>

}
