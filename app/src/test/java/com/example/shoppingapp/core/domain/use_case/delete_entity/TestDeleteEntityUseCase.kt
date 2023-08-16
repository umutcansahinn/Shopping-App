package com.example.shoppingapp.core.domain.use_case.delete_entity

import com.example.shoppingapp.core.data.repository.FakeShoppingRepository
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.data.source.local.RatingEntity
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCaseImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestDeleteEntityUseCase {
    private lateinit var fakeShoppingRepository: FakeShoppingRepository
    private lateinit var deleteEntityUseCase: DeleteEntityUseCaseImpl

    @Before
    fun setup() {
        fakeShoppingRepository = FakeShoppingRepository()
        deleteEntityUseCase = DeleteEntityUseCaseImpl(fakeShoppingRepository)
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
        fakeShoppingRepository.insertEntity(entity1)
        deleteEntityUseCase.invoke(entity1)
        val result = fakeShoppingRepository.getAllBasketEntities().first()
        Truth.assertThat(result).hasSize(0)
        Truth.assertThat(result).doesNotContain(entity1)
    }

}