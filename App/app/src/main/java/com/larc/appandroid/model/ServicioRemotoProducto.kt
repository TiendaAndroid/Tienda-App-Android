package com.larc.appandroid.model

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServicioRemotoProducto {

    /*
    // Define the certificate pinning
    private val certificatePinner by lazy {
        CertificatePinner.Builder()
            .add("backend-tienda-production.up.railway.app", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build()
    }
     */

    // Function to create an OkHttpClient
    private val okHttpClient by lazy {
        // Optional: Add a logging interceptor for debugging purposes
        /*
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Logs request and response body
        }
         */

        OkHttpClient.Builder()
            //.addInterceptor(logging) // Add the logging interceptor
            //.certificatePinner(certificatePinner) // Add certificate pinning
            .connectTimeout(15, TimeUnit.SECONDS) // Customize the timeouts
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
        retrofit.create(ProductoAPI::class.java)
    }

    // Descarga lista completa
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

    // Descarga lista de productos
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

    // Descarga lista de productos por categoría
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

    // Descarga producto por id
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