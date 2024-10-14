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

    /**
     * Crea una nueva dirección.
     *
     * Envía una solicitud para crear una nueva dirección para el usuario autenticado.
     *
     * @param token El token de autorización para autenticar la solicitud.
     * @param direccionCompletaRequest El objeto que contiene la información completa de la nueva dirección.
     * @return La respuesta del servidor con los detalles de la dirección creada.
     */
    @POST("directions")
    suspend fun createAddress(
        @Header("Authorization") token: String,
        @Body direccionCompletaRequest: DireccionCompletaRequest
    ): Response<DireccionCompletaResponse>

    /**
     * Elimina una dirección.
     *
     * Elimina una dirección específica usando su ID, para el usuario autenticado.
     *
     * @param token El token de autorización para autenticar la solicitud.
     * @param id El ID de la dirección que se va a eliminar.
     * @return La respuesta del servidor indicando si la eliminación fue exitosa.
     */
    @DELETE("directions/{id}")
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteAddressResponse>

    /**
     * Consulta una dirección específica.
     *
     * Obtiene los detalles de una dirección específica mediante su ID, para el usuario autenticado.
     *
     * @param token El token de autorización para autenticar la solicitud.
     * @param id El ID de la dirección que se va a consultar.
     * @return La respuesta del servidor con los detalles de la dirección consultada.
     */
    @GET("directions/{id}")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<GetAddressResponse>

}
