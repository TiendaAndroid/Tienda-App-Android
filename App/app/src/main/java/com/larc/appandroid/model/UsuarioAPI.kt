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

    // Ingresar a la app
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<UsuarioResponse>

    // Solicitar envío de correo para registro
    @POST("auth/email")
    suspend fun signupEmail(
        @Body signupRequest: SignupRequest
    ): Response<List<String>>

    // Registrarse
    @POST("auth/register")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<UsuarioResponseRegister>

    // Consultar perfil
    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

}