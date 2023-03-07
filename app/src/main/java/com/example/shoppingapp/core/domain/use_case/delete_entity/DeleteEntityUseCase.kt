package com.example.shoppingapp.core.domain.use_case.delete_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity

interface DeleteEntityUseCase {
    suspend operator fun invoke(entity: BasketEntity)
}