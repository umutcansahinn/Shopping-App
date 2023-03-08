package com.example.shoppingapp.core.domain.use_case.get_all_entity

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface GetAllEntityUseCase {
    operator fun invoke(): Resource<Flow<List<BasketEntity>>>
}