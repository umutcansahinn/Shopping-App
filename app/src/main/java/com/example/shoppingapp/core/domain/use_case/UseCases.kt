package com.example.shoppingapp.core.domain.use_case

import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.get_all_entity.GetAllEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.insert_entity.InsertEntityUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCaseImpl
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCaseImpl

data class UseCases(
    val getAllProductsUseCase: GetAllProductsUseCaseImpl,
    val getSingleProductUseCase: GetSingleProductUseCaseImpl,
    val insertEntityUseCase: InsertEntityUseCaseImpl,
    val deleteEntityUseCase: DeleteEntityUseCaseImpl,
    val getAllEntityUseCase: GetAllEntityUseCaseImpl,
    val updateEntityUseCase: UpdateEntityUseCaseImpl
)
