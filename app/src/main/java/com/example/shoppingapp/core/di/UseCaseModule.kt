package com.example.shoppingapp.core.di

import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import com.example.shoppingapp.core.domain.use_case.UseCases
import com.example.shoppingapp.core.domain.use_case.delete_all_entity.DeleteAllEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.get_all_entity.GetAllEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.insert_entity.InsertEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideUseCases(
        shoppingRepository: ShoppingRepository,
        shoppingListMapper: ShoppingListMapper<ProductDto, ProductUiModel>,
        shoppingMapper: ShoppingMapper<ProductDto, ProductUiModel>
    ): UseCases {
        return UseCases(
            getAllProductsUseCase = GetAllProductsUseCaseImpl(
                shoppingRepository = shoppingRepository,
                shoppingListMapper = shoppingListMapper
            ),
            getSingleProductUseCase = GetSingleProductUseCaseImpl(
                shoppingRepository = shoppingRepository,
                shoppingMapper = shoppingMapper
            ),
            insertEntityUseCase = InsertEntityUseCaseImpl(
                shoppingRepository = shoppingRepository
            ),
            deleteEntityUseCase = DeleteEntityUseCaseImpl(
                shoppingRepository = shoppingRepository
            ),
            getAllEntityUseCase = GetAllEntityUseCaseImpl(
                shoppingRepository = shoppingRepository
            ),
            updateEntityUseCase = UpdateEntityUseCaseImpl(
                shoppingRepository = shoppingRepository
            ),
            deleteAllEntityUseCase = DeleteAllEntityUseCaseImpl(
                shoppingRepository = shoppingRepository
            )
        )
    }
}