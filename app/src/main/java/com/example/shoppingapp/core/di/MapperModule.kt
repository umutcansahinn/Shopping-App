package com.example.shoppingapp.core.di

import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.mapper.all_products.ShoppingListMapperImpl
import com.example.shoppingapp.core.domain.mapper.single_product.ShoppingMapperImpl
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindShoppingListMapper(
        shoppingListMapperImpl: ShoppingListMapperImpl
    ): ShoppingListMapper<ProductDto, DomainModel>


    @Binds
    abstract fun bindShoppingMapper(
        shoppingMapperImpl: ShoppingMapperImpl
    ): ShoppingMapper<ProductDto,DomainModel>
}