package com.example.shoppingapp.core.domain.use_case.insert_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import javax.inject.Inject

class InsertEntityUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : InsertEntityUseCase {
    override suspend fun invoke(entity: BasketEntity) {
        shoppingRepository.insertEntity(entity = entity)
    }
}

