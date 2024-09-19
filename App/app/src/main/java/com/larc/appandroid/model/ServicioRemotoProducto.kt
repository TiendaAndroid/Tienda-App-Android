package com.larc.appandroid.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ServicioRemotoProducto @Inject constructor() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-tienda-production.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicio by lazy {
        retrofit.create(ProductoAPI::class.java)
    }

<<<<<<< HEAD
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
=======
    // Descarga lista de productos
    suspend fun getProductos(offset: Int): List<Producto> {
        return servicio.getProductos(offset = offset).data
    }
    // Descarga número de páginas
    suspend fun getHowManyPages(offset: Int): Int {
        return servicio.getProductos(offset = offset).totalResults
    }
    // Descarga lista de productos por categoría
    suspend fun getProductosPorCategoria(categoria: String, offset: Int): List<Producto> {
        return servicio.getProductosPorCategoria(categoria, offset = offset).data
    }
    // Descarga número de páginas por categoría
    suspend fun getHowManyPagesPorCategoria(categoria: String, offset: Int): Int {
        return servicio.getProductosPorCategoria(categoria, offset = offset).totalResults
>>>>>>> parent of d6fcd22 (MALDITACORRECCIONDEMIERDA)
    }
}
