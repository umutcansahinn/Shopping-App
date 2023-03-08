package com.example.shoppingapp.core.domain.use_case.delete_all_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCase
import javax.inject.Inject

class DeleteAllEntityUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : DeleteAllEntityUseCase {
    override suspend fun invoke() {
        shoppingRepository.deleteAllEntity()
    }

}