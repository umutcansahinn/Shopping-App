package com.example.shoppingapp.feature.basket

import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.feature.home_detail.SingleProductState

sealed class BasketState {
    object Loading : BasketState()
    data class Success(val entities: List<BasketEntity>) : BasketState()
    data class Error(val error: String) : BasketState()
}
