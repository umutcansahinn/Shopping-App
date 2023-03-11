package com.example.shoppingapp.core.domain.use_case.gel_all_products

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val shoppingListMapper: ShoppingListMapper<ProductDto, DomainModel>
) : GetAllProductsUseCase {
    override fun invoke(): Flow<Resource<List<DomainModel>>> = flow {
        try {
            emit(Resource.Loading)
            val result = shoppingListMapper.map(shoppingRepository.getAllProducts())
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}