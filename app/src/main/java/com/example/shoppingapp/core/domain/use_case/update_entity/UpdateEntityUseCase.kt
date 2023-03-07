package com.example.shoppingapp.core.domain.use_case.update_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity

interface UpdateEntityUseCase {
    suspend operator fun invoke(entity: BasketEntity)
}