package com.example.shoppingapp.core.domain.use_case.get_all_products

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.remote.model.Rating
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAllProductsUseCase : GetAllProductsUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(): Flow<Resource<List<DomainModel>>> = flow {
        emit(Resource.Loading)

        if (showError) {
            emit(Resource.Error("Error"))
        } else {
            emit(
                Resource.Success(
                    listOf(
                        DomainModel(
                            "category",
                            "description",
                            1,
                            "image",
                            1.0,
                            Rating(1, 1.0),
                            "title"
                        )
                    )
                )
            )
        }
    }
}