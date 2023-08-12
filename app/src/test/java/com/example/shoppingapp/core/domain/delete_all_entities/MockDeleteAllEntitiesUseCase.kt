package com.example.shoppingapp.core.domain.delete_all_entities

import com.example.shoppingapp.core.data.repository.FakeShoppingRepository
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import com.example.shoppingapp.core.domain.use_case.delete_all_entities.DeleteAllEntitiesUseCase
import com.example.shoppingapp.core.domain.use_case.delete_all_entities.DeleteAllEntitiesUseCaseImpl
import org.junit.Before

class MockDeleteAllEntitiesUseCase {

    private lateinit var mockShoppingRepository: ShoppingRepository
    private lateinit var deleteAllEntitiesUseCase: DeleteAllEntitiesUseCase

    @Before
    fun setup() {
        mockShoppingRepository = FakeShoppingRepository()
        deleteAllEntitiesUseCase = DeleteAllEntitiesUseCaseImpl(mockShoppingRepository)
    }
}