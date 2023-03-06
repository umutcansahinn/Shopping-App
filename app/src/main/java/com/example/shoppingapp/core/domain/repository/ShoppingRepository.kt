package com.example.shoppingapp.core.domain.repository

import com.example.shoppingapp.core.data.model.ProductDto

interface ShoppingRepository {

    suspend fun getAllProducts(): List<ProductDto>

    suspend fun getProductById(id: Int): ProductDto
}