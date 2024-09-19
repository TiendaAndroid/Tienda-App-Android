package com.larc.appandroid.model.repository

import com.larc.appandroid.model.ProductoResponse
import com.larc.appandroid.model.ServicioRemotoProducto
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val servicioRemotoProducto: ServicioRemotoProducto
) {

    // Get all products
    suspend fun getAllProducts(offset: Int): ProductoResponse? {
        return servicioRemotoProducto.getProductos(offset)
    }

    // Get products by category
    suspend fun getProductsByCategory(category: String, offset: Int): ProductoResponse? {
        return servicioRemotoProducto.getProductosPorCategoria(category, offset)
    }
}
