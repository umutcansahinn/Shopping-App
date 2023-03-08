package com.example.shoppingapp.core.domain.use_case.get_all_entity

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllEntityUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : GetAllEntityUseCase{

    override fun invoke(): Resource<Flow<List<BasketEntity>>> {
        return try {
            Resource.Loading
            Resource.Success(shoppingRepository.getAllBasketEntity())
        }catch (e:Exception) {
            Resource.Error(e.localizedMessage.orEmpty())
        }
    }
}