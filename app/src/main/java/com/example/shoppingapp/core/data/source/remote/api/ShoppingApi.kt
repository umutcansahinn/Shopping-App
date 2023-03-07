package com.example.shoppingapp.core.data.source.remote.api

import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppingApi {

    @GET("products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}