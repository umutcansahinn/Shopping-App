package com.example.shoppingapp.core.di

import com.example.shoppingapp.core.data.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import com.example.shoppingapp.core.domain.use_case.UseCases
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCase
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCase
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetAllProductsUseCase(
        getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl
    ): GetAllProductsUseCase

    @Binds
    abstract fun bindGetSingleProductUseCase(
        getSingleProductUseCaseImpl: GetSingleProductUseCaseImpl
    ): GetSingleProductUseCase
}


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideUseCases(
        shoppingRepository: ShoppingRepository,
        shoppingListMapper: ShoppingListMapper<ProductDto,ProductUiModel>,
        shoppingMapper: ShoppingMapper<ProductDto,ProductUiModel>
    ): UseCases {
        return UseCases(
            getAllProductsUseCase = GetAllProductsUseCaseImpl(
                shoppingRepository = shoppingRepository,
                shoppingListMapper = shoppingListMapper
            ),
            getSingleProductUseCase = GetSingleProductUseCaseImpl(
                shoppingRepository = shoppingRepository,
                shoppingMapper = shoppingMapper
            )
        )
    }
}

