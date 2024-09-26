package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioAPI {

    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<UsuarioResponse>

    @POST("auth/email")
    suspend fun signupEmail(
        @Body signupRequest: SignupRequest
    ): Response<List<String>>

}