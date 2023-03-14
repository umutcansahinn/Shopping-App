package com.example.shoppingapp.core.domain.use_case.delete_all_entities

import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class DeleteAllEntitiesUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : DeleteAllEntitiesUseCase {
    override suspend fun invoke() {
        shoppingRepository.deleteAllEntities()
    }

}