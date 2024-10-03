package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Representa las llamadas a la API relacionadas con usuarios.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

interface UsuarioAPI {

    /**
     * Inicia sesión en la aplicación.
     *
     * Envía una solicitud de inicio de sesión con las credenciales del usuario (correo y contraseña).
     *
     * @param loginRequest El objeto que contiene las credenciales de inicio de sesión.
     * @return Un objeto `UsuarioResponse` si la operación fue exitosa, de lo contrario un error en la respuesta.
     */
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<UsuarioResponse>

    /**
     * Solicita el envío de un correo electrónico para completar el registro.
     *
     * Envía una solicitud con la información del usuario para que se le envíe un correo con un token de verificación.
     *
     * @param signupRequest El objeto que contiene la información del usuario (nombre y correo).
     * @return Una lista de cadenas con la respuesta del servidor si la operación fue exitosa.
     */
    @POST("auth/email")
    suspend fun signupEmail(
        @Body signupRequest: SignupRequest
    ): Response<List<String>>

    /**
     * Completa el registro de un usuario.
     *
     * Envía una solicitud con la información completa del usuario y el token de verificación previamente enviado por correo.
     *
     * @param registerRequest El objeto que contiene la información completa del usuario y el token de verificación.
     * @return Un objeto `UsuarioResponseRegister` si la operación fue exitosa, de lo contrario un error en la respuesta.
     */
    @POST("auth/register")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<UsuarioResponseRegister>

    /**
     * Consulta el perfil del usuario autenticado.
     *
     * Envía una solicitud para obtener los detalles del perfil del usuario utilizando un token de autorización.
     *
     * @param token El token de autenticación en formato `Bearer`.
     * @return Un objeto `ProfileResponse` con los detalles del perfil si la operación fue exitosa, de lo contrario un error en la respuesta.
     */
    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

}