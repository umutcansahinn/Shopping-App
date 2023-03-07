package com.example.shoppingapp.core.domain.use_case.delete_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class DeleteEntityUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : DeleteEntityUseCase{
    override suspend fun invoke(entity: BasketEntity) {
        shoppingRepository.deleteEntity(entity = entity)
    }
}