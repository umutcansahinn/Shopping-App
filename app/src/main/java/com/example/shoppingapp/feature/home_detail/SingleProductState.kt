package com.example.shoppingapp.feature.home_detail

import com.example.shoppingapp.core.domain.modelUi.ProductUiModel

sealed class SingleProductState {
    object Loading : SingleProductState()
    data class Success(val products: ProductUiModel) : SingleProductState()
    data class Error(val error: String) : SingleProductState()
}