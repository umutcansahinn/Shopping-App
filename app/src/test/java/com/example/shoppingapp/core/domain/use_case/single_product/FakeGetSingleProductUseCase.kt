package com.example.shoppingapp.core.domain.use_case.single_product

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.remote.model.Rating
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSingleProductUseCase : GetSingleProductUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(id: Int): Flow<Resource<DomainModel>> = flow {

        emit(Resource.Loading)

        if (showError) {
            emit(Resource.Error("Error"))
        } else {
            emit(
                Resource.Success(
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
        }
    }
}
