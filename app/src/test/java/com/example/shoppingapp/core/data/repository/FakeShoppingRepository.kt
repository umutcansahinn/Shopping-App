package com.example.shoppingapp.core.data.repository

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeShoppingRepository : ShoppingRepository {

    private val basketEntities = mutableListOf<BasketEntity>()
    private val products = mutableListOf<ProductDto>().apply {
        add(
            0, ProductDto(
                null,
                null,
                1,
                null,
                null,
                null,
                null
            )
        )
        add(
            1, ProductDto(
                null,
                null,
                2,
                null,
                null,
                null,
                null
            )
        )
    }


    override suspend fun getAllProducts(): List<ProductDto> {
        return products
    }

    override suspend fun getProductById(id: Int): ProductDto {
        return if (id >= 1 && id <= products.size) {
            products[id - 1]
        } else {
            throw NoSuchElementException("Product with id $id not found")
        }
    }

    override suspend fun insertEntity(entity: BasketEntity) {
        basketEntities.add(entity)
    }

    override suspend fun deleteEntity(entity: BasketEntity) {
        basketEntities.remove(entity)
    }

    override suspend fun deleteAllEntities() {
        basketEntities.clear()
    }

    override suspend fun updateEntity(entity: BasketEntity) {
        val index = basketEntities.indexOfFirst { it.id == entity.id }
        if (index != -1) {
            basketEntities[index] = entity
        }
    }

    override fun getAllBasketEntities(): Flow<List<BasketEntity>> {
        return flowOf(basketEntities)
    }
}