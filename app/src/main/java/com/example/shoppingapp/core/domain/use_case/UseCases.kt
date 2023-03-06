package com.example.shoppingapp.core.domain.use_case

import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCaseImpl

data class UseCases(
    val getAllProductsUseCase: GetAllProductsUseCaseImpl,
    val getSingleProductUseCase: GetSingleProductUseCaseImpl
)
