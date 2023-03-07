package com.example.shoppingapp.core.domain.use_case.update_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class UpdateEntityUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : UpdateEntityUseCase{
    override suspend fun invoke(entity: BasketEntity) {
        shoppingRepository.updateEntity(entity = entity)
    }
}