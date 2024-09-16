package com.larc.appandroid.model

import retrofit2.http.GET

interface ProductoAPI {
    @GET("products")
    suspend fun getProductos(): ProductoResponse //List<Producto>
}