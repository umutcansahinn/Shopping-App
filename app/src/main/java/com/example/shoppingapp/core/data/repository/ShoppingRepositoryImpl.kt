package com.example.shoppingapp.core.data.repository

import com.example.shoppingapp.core.data.source.local.BasketDao
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.remote.api.ShoppingApi
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingApi: ShoppingApi,
    private val basketDao: BasketDao
) : ShoppingRepository {


    override suspend fun getAllProducts(): List<ProductDto> {
        return shoppingApi.getAllProducts()
    }

    override suspend fun getProductById(id: Int): ProductDto {
        return shoppingApi.getProductById(id = id)
    }

    override suspend fun insertEntity(entity: BasketEntity) {
        basketDao.insertBasketEntity(entity = entity)
    }

    override suspend fun deleteEntity(entity: BasketEntity) {
        basketDao.deleteBasketEntity(entity = entity)
    }

    override suspend fun updateEntity(entity: BasketEntity) {
        basketDao.updateBasketEntity(entity = entity)
    }

    override suspend fun getAllBasketEntity(): List<BasketEntity> {
        return basketDao.getAllBasketEntity()
    }
}