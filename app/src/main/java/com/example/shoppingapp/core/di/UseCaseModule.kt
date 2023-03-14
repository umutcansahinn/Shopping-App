package com.example.shoppingapp.core.di

import com.example.shoppingapp.core.domain.use_case.delete_all_entities.DeleteAllEntitiesUseCase
import com.example.shoppingapp.core.domain.use_case.delete_all_entities.DeleteAllEntitiesUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCase
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCase
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.get_all_entities.GetAllEntitiesUseCase
import com.example.shoppingapp.core.domain.use_case.get_all_entities.GetAllEntitiesUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.insert_entity.InsertEntityUseCase
import com.example.shoppingapp.core.domain.use_case.insert_entity.InsertEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCase
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCase
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCaseImpl
import dagger.Binds
import dagger.Module
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

    @Binds
    abstract fun bindDeleteEntityUseCase(
        deleteEntityUseCaseImpl: DeleteEntityUseCaseImpl
    ): DeleteEntityUseCase

    @Binds
    abstract fun bindUpdateEntityUseCase(
        updateEntityUseCaseImpl: UpdateEntityUseCaseImpl
    ): UpdateEntityUseCase

    @Binds
    abstract fun bindInsertEntityUseCase(
        insertEntityUseCaseImpl: InsertEntityUseCaseImpl
    ): InsertEntityUseCase

    @Binds
    abstract fun bindGetAllEntityUseCase(
        getAllEntityUseCaseImpl: GetAllEntitiesUseCaseImpl
    ): GetAllEntitiesUseCase

    @Binds
    abstract fun bindDeleteAllEntityUseCase(
        deleteAllEntityUseCaseImpl: DeleteAllEntitiesUseCaseImpl
    ): DeleteAllEntitiesUseCase
}
