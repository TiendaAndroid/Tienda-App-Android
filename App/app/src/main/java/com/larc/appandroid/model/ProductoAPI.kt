package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Representa las llamadas a la API relacionadas con productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

interface ProductoAPI {

    /**
     * Consulta una lista de productos, paginados de 5 en 5.
     *
     * Obtiene un conjunto de productos con un límite y un desplazamiento específicos.
     *
     * @param limit El número máximo de productos a devolver por página. El valor predeterminado es 5.
     * @param offset El número de productos a saltar (usado para la paginación).
     * @return La respuesta del servidor con los productos solicitados.
     */
    @GET("products")
    suspend fun getProductos(
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    /**
     * Consulta una lista de productos por categoría, paginados de 5 en 5.
     *
     * Obtiene un conjunto de productos pertenecientes a una categoría específica, con un límite y un desplazamiento específicos.
     *
     * @param categoria El nombre de la categoría a consultar.
     * @param limit El número máximo de productos a devolver por página. El valor predeterminado es 5.
     * @param offset El número de productos a saltar (usado para la paginación).
     * @return La respuesta del servidor con los productos solicitados de la categoría.
     */
    @GET("products/tipo/{categoria}")
    suspend fun getProductosPorCategoria(
        @Path("categoria") categoria: String,
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int
    ): Response<ProductoResponse>

    /**
     * Consulta un producto por su ID.
     *
     * Obtiene los detalles de un producto específico utilizando su ID.
     *
     * @param id El ID del producto que se desea consultar.
     * @return La respuesta del servidor con los detalles del producto.
     */
    @GET("products/{id}")
    suspend fun getProductoPorId(
        @Path("id") id: String
    ): Response<Producto>

    /**
     * Consulta productos por palabra clave.
     *
     * Realiza una búsqueda de productos basada en una palabra clave proporcionada.
     *
     * @param palabra La palabra clave a buscar en los productos.
     * @return La respuesta del servidor con los productos que coincidan con la búsqueda.
     */
    @GET("products/search/{palabra}")
    suspend fun searchProducts(
        @Path("palabra") palabra: String
    ): Response<ProductoResponse>
}