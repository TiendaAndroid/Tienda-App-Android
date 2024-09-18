package com.larc.appandroid.model

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
    }
}