package com.example.shoppingapp.core.domain.use_case.single_product

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingMapper
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val shoppingMapper: ShoppingMapper<ProductDto, DomainModel>

) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<Resource<DomainModel>> {
        return flow {
            try {
                emit(Resource.Loading)
                val result = shoppingMapper.map(shoppingRepository.getProductById(id = id))
                emit(Resource.Success(result))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage.orEmpty()))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}