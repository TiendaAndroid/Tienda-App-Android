package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicioRemotoProducto {

    // Objeto retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
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