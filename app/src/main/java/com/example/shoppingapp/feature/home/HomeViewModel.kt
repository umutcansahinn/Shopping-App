package com.example.shoppingapp.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = MutableLiveData<AllProductsState>()
    val state: LiveData<AllProductsState> = _state


    fun getAllProducts() {
        viewModelScope.launch {
            useCases.getAllProductsUseCase().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = AllProductsState.Success(products = result.data)
                    }
                    is Resource.Loading -> {
                        _state.value = AllProductsState.Loading
                    }
                    is Resource.Error -> {
                        _state.value = AllProductsState.Error(error = result.errorMessage)
                    }
                }
            }
        }
    }
}