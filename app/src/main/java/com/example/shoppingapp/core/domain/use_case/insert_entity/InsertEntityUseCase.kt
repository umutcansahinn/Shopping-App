package com.example.shoppingapp.core.domain.use_case.insert_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity

interface InsertEntityUseCase {
    suspend operator fun invoke(entity: BasketEntity)
}