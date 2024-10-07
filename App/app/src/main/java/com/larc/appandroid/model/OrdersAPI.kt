package com.larc.appandroid.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OrdersAPI {

    @GET("orders/{id}")
    suspend fun getOrder(
        @Path("id") id: String
    ): Response<Order>

}
