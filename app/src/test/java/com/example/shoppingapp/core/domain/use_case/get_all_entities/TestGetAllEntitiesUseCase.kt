package com.example.shoppingapp.core.domain.use_case.get_all_entities

import com.example.shoppingapp.core.data.repository.FakeShoppingRepository
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestGetAllEntitiesUseCase {

    private lateinit var fakeShoppingRepository: FakeShoppingRepository
    private lateinit var getAllEntitiesUseCaseImpl: GetAllEntitiesUseCaseImpl

    @Before
    fun setup() {
        fakeShoppingRepository = FakeShoppingRepository()
        getAllEntitiesUseCaseImpl = GetAllEntitiesUseCaseImpl(fakeShoppingRepository)
    }

    @Test
    fun test() = runBlocking {

        val entity1 = BasketEntity(
            "category1",
            "description1",
            1,
            "image1",
            1.0,
            RatingEntity(1, 1.0),
            "title1",
            1
        )
        val entity2 = BasketEntity(
            "category2",
            "description2",
            2,
            "image2",
            2.0,
            RatingEntity(2, 2.0),
            "title2",
            2
        )
        fakeShoppingRepository.insertEntity(entity1)
        fakeShoppingRepository.insertEntity(entity2)
        val result =fakeShoppingRepository.getAllBasketEntities().first()
        assertThat(result).hasSize(2)
        assertThat(result).contains(entity1)
        assertThat(result).contains(entity2)
    }
}