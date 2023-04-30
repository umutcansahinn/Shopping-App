package com.example.shoppingapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.domain.domain_model.DomainModel
import com.example.shoppingapp.core.domain.use_case.gel_all_products.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _productList = MutableStateFlow<Resource<List<DomainModel>>>(Resource.Loading)
    val productList get() = _productList.asStateFlow()

    init {
        getAllProducts()
    }
    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().collect {
                _productList.value = it
            }
        }
    }
}