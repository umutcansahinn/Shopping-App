package com.example.shoppingapp.core.domain.use_case.gel_all_products

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    operator fun invoke(): Flow<Resource<List<ProductUiModel>>>
}