package com.larc.appandroid.model

import android.util.Log
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServicioRemotoUsuario {

    // Define the certificate pinning
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Objeto retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Objeto para descargar un dato o servicio
    private val servicio by lazy {
        retrofit.create(UsuarioAPI::class.java)
    }

    // Método para iniciar sesión
    suspend fun loginUser(loginRequest: LoginRequest): UsuarioResponse? {
        return try {
            val response: Response<UsuarioResponse> = servicio.loginUser(loginRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // Método para registrarse (recibir token por correo)
    suspend fun signupEmail(signupRequest: SignupRequest): List<String>? {
        return try {
            val response: Response<List<String>> = servicio.signupEmail(signupRequest)
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("SignupResponse", "Response body: $responseBody")
                responseBody
            } else {
                Log.d("SignupResponse", "Error: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("SignupResponse", "Exception: ${e.message}")
            null
        }
    }
}