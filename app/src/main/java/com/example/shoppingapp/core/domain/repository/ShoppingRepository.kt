package com.example.shoppingapp.core.domain.repository

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.remote.model.ProductDto

interface ShoppingRepository {

    suspend fun getAllProducts(): List<ProductDto>

    suspend fun getProductById(id: Int): ProductDto

    suspend fun insertEntity(entity: BasketEntity)

    suspend fun deleteEntity(entity: BasketEntity)

    suspend fun updateEntity(entity: BasketEntity)

    suspend fun getAllBasketEntity(): List<BasketEntity>
}