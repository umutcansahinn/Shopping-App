package com.example.shoppingapp.core.domain.use_case.get_all_entities

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import kotlinx.coroutines.flow.Flow

interface GetAllEntitiesUseCase {
    operator fun invoke(): Resource<Flow<List<BasketEntity>>>
}