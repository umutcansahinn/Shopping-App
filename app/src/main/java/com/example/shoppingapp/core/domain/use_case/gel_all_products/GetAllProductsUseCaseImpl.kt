package com.example.shoppingapp.core.domain.use_case.gel_all_products

import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.remote.model.ProductDto
import com.example.shoppingapp.core.domain.mapper.ShoppingListMapper
import com.example.shoppingapp.core.domain.modelUi.ProductUiModel
import com.example.shoppingapp.core.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val shoppingListMapper: ShoppingListMapper<ProductDto, ProductUiModel>
) : GetAllProductsUseCase {
    override fun invoke(): Flow<Resource<List<ProductUiModel>>> {
        return flow {
            try {
                emit(Resource.Loading)
                shoppingRepository.getAllProducts().also {
                    emit(Resource.Success(shoppingListMapper.map(input = it)))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage.orEmpty()))
            }catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}