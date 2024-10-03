package com.larc.appandroid.model

import android.util.Log
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Representa la estructura para el servicio remoto de usuarios.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class ServicioRemotoUsuario {

    /**
     * Define el *certificate pinning* para asegurar la comunicación con el servidor.
     */
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .build()
    }

    /**
     * Cliente OkHttp configurado con *certificate pinning* y tiempos de espera.
     */
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Instancia Retrofit configurada para interactuar con la API de usuarios.
     * Utiliza el cliente OkHttp previamente configurado.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Servicio que interactúa con la API de usuarios.
     */
    private val servicio by lazy {
        retrofit.create(UsuarioAPI::class.java)
    }

    /**
     * Inicia sesión para un usuario.
     *
     * @param loginRequest El objeto que contiene las credenciales de inicio de sesión (correo y contraseña).
     * @return Un objeto `UsuarioResponse` si la operación fue exitosa, de lo contrario `null`.
     */
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

    /**
     * Solicita el registro de un usuario con su correo electrónico.
     *
     * Envía un correo electrónico con un token de verificación al usuario.
     *
     * @param signupRequest El objeto que contiene la información del usuario (nombre, correo).
     * @return Una lista de cadenas con la respuesta del servidor si la operación fue exitosa, de lo contrario `null`.
     */
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

    /**
     * Completa el registro de un usuario utilizando el token enviado al correo.
     *
     * @param registerRequest El objeto que contiene la información completa del usuario y el token de verificación.
     * @return Un objeto `UsuarioResponseRegister` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun registerUser(registerRequest: RegisterRequest): UsuarioResponseRegister? {
        return try {
            val response: Response<UsuarioResponseRegister> = servicio.registerUser(registerRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Obtiene el perfil del usuario autenticado.
     *
     * @param token El token de autenticación en formato `Bearer`.
     * @return Un objeto `ProfileResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun getProfile(token: String): ProfileResponse? {
        val bearerToken = "Bearer $token"
        return try {
            val response: Response<ProfileResponse> = servicio.getProfile(bearerToken)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}