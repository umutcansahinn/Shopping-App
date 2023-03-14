package com.example.shoppingapp.core.domain.use_case.get_all_entities

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEntitiesUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : GetAllEntitiesUseCase {

    override fun invoke(): Resource<Flow<List<BasketEntity>>> {
        Resource.Loading
        return try {
            Resource.Success(shoppingRepository.getAllBasketEntities())
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage.orEmpty())
        }
    }
}