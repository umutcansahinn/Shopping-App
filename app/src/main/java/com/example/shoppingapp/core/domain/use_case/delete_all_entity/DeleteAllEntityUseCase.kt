package com.example.shoppingapp.core.domain.use_case.delete_all_entity

import com.example.shoppingapp.core.data.source.local.BasketEntity

interface DeleteAllEntityUseCase {
    suspend operator fun invoke()
}