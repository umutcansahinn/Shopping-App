package com.example.shoppingapp.feature.home

import com.example.shoppingapp.core.domain.modelUi.ProductUiModel

sealed class AllProductsState {
    object Loading : AllProductsState()
    data class Success(val products: List<ProductUiModel>) : AllProductsState()
    data class Error(val error: String) : AllProductsState()
}
