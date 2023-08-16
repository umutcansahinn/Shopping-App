package com.example.shoppingapp.core.domain.use_case.update_entity

import com.example.shoppingapp.core.data.repository.FakeShoppingRepository
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCaseImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestUpdateEntityUseCase {

    private lateinit var fakeShoppingRepository: FakeShoppingRepository
    private lateinit var updateEntityUseCaseImpl: UpdateEntityUseCaseImpl

    @Before
    fun setup() {
        fakeShoppingRepository = FakeShoppingRepository()
        updateEntityUseCaseImpl = UpdateEntityUseCaseImpl(fakeShoppingRepository)
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
        val entity2 = entity1.copy("description2")
        fakeShoppingRepository.insertEntity(entity1)
        fakeShoppingRepository.updateEntity(entity2)
        val result = fakeShoppingRepository.getAllBasketEntities().first()
        assertThat(result).hasSize(1)
        assertThat(result).contains(entity2)
        assertThat(result).doesNotContain(entity1)
    }
}