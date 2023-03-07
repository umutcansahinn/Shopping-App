package com.example.shoppingapp.feature.home_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = MutableLiveData<SingleProductState>()
    val state: LiveData<SingleProductState> = _state

    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            useCases.getSingleProductUseCase(id = id).collect {result->
                when(result) {
                    is Resource.Success -> {
                        _state.value = SingleProductState.Success(products = result.data)
                    }
                    is Resource.Loading -> {
                        _state.value = SingleProductState.Loading
                    }
                    is Resource.Error -> {
                        _state.value = SingleProductState.Error(error = result.errorMessage)
                    }
                }
            }
        }
    }

    fun addEntityBasket(entity: BasketEntity) {
        viewModelScope.launch {
            useCases.insertEntityUseCase(entity = entity)
        }
    }
}
