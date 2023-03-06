package com.example.shoppingapp.core.domain.use_case.single_product

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface GetSingleProductUseCase {
    operator fun invoke(id: Int): Flow<Resource<ProductUiModel>>
}