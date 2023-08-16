package com.example.shoppingapp.core.data.repository

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingRepositoryTest {

    private lateinit var fakeShoppingRepository: ShoppingRepository
    private lateinit var entityFirst: BasketEntity
    private lateinit var entitySecond: BasketEntity

    @Before
    fun setup() {
        fakeShoppingRepository = FakeShoppingRepository()
        entityFirst = BasketEntity(
            category = "c",
            description = "d",
            id = 1,
            image = "i",
            price = 0.0,
            RatingEntity(count = 0, rate = 0.0),
            title = "t",
            itemCount = 1
        )
        entitySecond = BasketEntity(
            category = "c",
            description = "d",
            id = 2,
            image = "i",
            price = 0.0,
            RatingEntity(count = 0, rate = 0.0),
            title = "t",
            itemCount = 1
        )
    }

    @Test
    fun testGetAllProducts() = runBlocking {
        val result = fakeShoppingRepository.getAllProducts()
        assertThat(result).hasSize(2)
    }

    @Test
    fun testGetProductById() = runBlocking {
        val product = fakeShoppingRepository.getProductById(1)
        assertThat(product).isNotNull()
    }

    @Test
    fun testInsertEntity() = runBlocking {
        fakeShoppingRepository.insertEntity(entityFirst)
        val result = fakeShoppingRepository.getAllBasketEntities().first()

        assertThat(result).contains(entityFirst)
    }

    @Test
    fun testDeleteEntity() = runBlocking {
        fakeShoppingRepository.insertEntity(entityFirst)
        fakeShoppingRepository.deleteEntity(entityFirst)
        val result = fakeShoppingRepository.getAllBasketEntities().first()

        //  assertThat(result).doesNotContain(entity)
        assertThat(result).isEmpty()
    }

    @Test
    fun testDeleteAllEntity() = runBlocking {
        fakeShoppingRepository.insertEntity(entityFirst)
        fakeShoppingRepository.insertEntity(entitySecond)
        fakeShoppingRepository.deleteAllEntities()

        val result = fakeShoppingRepository.getAllBasketEntities().first()
        assertThat(result).isEmpty()
    }

    @Test
    fun testUpdateEntity() = runBlocking {
        fakeShoppingRepository.insertEntity(entityFirst)
        val updateEntity = entityFirst.copy(description = "update")
        fakeShoppingRepository.updateEntity(updateEntity)

        val result = fakeShoppingRepository.getAllBasketEntities().first()
        assertThat(result).contains(updateEntity)
    }

    @Test
    fun testGetAllBasketEntities() = runBlocking {
        val result = fakeShoppingRepository.getAllBasketEntities().first()
        assertThat(result).isEmpty()
    }
}