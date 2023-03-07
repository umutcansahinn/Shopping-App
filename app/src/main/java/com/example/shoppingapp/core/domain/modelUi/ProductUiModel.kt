package com.example.shoppingapp.core.domain.modelUi

import com.example.shoppingapp.core.data.source.remote.model.Rating

data class ProductUiModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
