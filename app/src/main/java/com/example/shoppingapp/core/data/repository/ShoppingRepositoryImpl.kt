package com.example.shoppingapp.core.data.repository

import com.example.shoppingapp.core.data.api.ShoppingApi
import com.example.shoppingapp.core.data.model.ProductDto
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingApi: ShoppingApi
): ShoppingRepository {


    override suspend fun getAllProducts(): List<ProductDto> {
        return shoppingApi.getAllProducts()
    }

    override suspend fun getProductById(id: Int): ProductDto {
        return shoppingApi.getProductById(id = id)
    }
}