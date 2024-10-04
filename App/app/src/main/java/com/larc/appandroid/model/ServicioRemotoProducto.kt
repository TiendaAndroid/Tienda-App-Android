package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Representa la estructura para el servicio remoto de productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class ServicioRemotoProducto {

    /**
     * Define el *certificate pinning* para asegurar la comunicación con el servidor.
     */
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            //.add("backend-tienda-production.up.railway.app", "sha256/FyVOgNsQG1rWPMMd3OLpZYcPsDlc5JxBQs59jmk8Vx4=")
            .add("fabm.online", "sha256/BTvVjAWnPX4FDA4NB0n6OlshgFbmV/5RAV8M8BFG/o8=")
            .build()
    }

    /**
     * Cliente OkHttp configurado con *certificate pinning* y tiempos de espera.
     * Incluye un interceptor opcional para logging en modo de depuración (comentado).
     */
    private val okHttpClient by lazy {
        // Debugging
        /*
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        */

        OkHttpClient.Builder()
            //.addInterceptor(logging) // Interceptor para logging (opcional)
            .certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Instancia Retrofit configurada para interactuar con la API de productos.
     * Utiliza el cliente OkHttp previamente configurado.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            //.baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .baseUrl("https://fabm.online/zazil_backend/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Servicio que interactúa con la API de productos.
     */
    private val servicio by lazy {
        retrofit.create(ProductoAPI::class.java)
    }

    /**
     * Busca productos utilizando una palabra clave.
     *
     * @param palabra La palabra clave a utilizar en la búsqueda de productos.
     * @return Un objeto `ProductoResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun searchProducts(palabra: String): ProductoResponse? {
        return try {
            val response: Response<ProductoResponse> = servicio.searchProducts(palabra = palabra)
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
     * Obtiene una lista de productos paginada.
     *
     * @param offset El número de productos a saltar (usado para la paginación).
     * @return Un objeto `ProductoResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun getProductos(offset: Int): ProductoResponse? {
        return try {
            val response: Response<ProductoResponse> = servicio.getProductos(offset = offset)
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
     * Obtiene una lista de productos por categoría, paginada.
     *
     * @param categoria La categoría de productos a consultar.
     * @param offset El número de productos a saltar (usado para la paginación).
     * @return Un objeto `ProductoResponse` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun getProductosPorCategoria(categoria: String, offset: Int): ProductoResponse? {
        return try {
            val response: Response<ProductoResponse> = servicio.getProductosPorCategoria(categoria, offset = offset)
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
     * Obtiene los detalles de un producto específico por su ID.
     *
     * @param id El ID del producto que se desea consultar.
     * @return Un objeto `Producto` si la operación fue exitosa, de lo contrario `null`.
     */
    suspend fun getProductoPorId(id: String): Producto? {
        return try {
            val response: Response<Producto> = servicio.getProductoPorId(id)
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