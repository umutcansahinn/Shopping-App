package com.example.shoppingapp.feature.home_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.core.domain.use_case.insert_entity.InsertEntityUseCase
import com.example.shoppingapp.core.domain.use_case.single_product.GetSingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val insertEntityUseCase: InsertEntityUseCase
) : ViewModel() {

    private val _singleProduct = MutableStateFlow<Resource<DomainModel>>(Resource.Loading)
    val singleProduct get() = _singleProduct.asStateFlow()

    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            getSingleProductUseCase(id = id).collect { result ->
                _singleProduct.value = result
            }
        }
    }

    fun insertEntity(entity: BasketEntity) {
        viewModelScope.launch {
            insertEntityUseCase(entity = entity)
        }
    }
}
