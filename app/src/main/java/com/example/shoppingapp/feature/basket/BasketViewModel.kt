package com.example.shoppingapp.feature.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


    private val _state = MutableLiveData<BasketState>()
    val state: LiveData<BasketState> = _state

    fun getEntityFromRoom() {
        viewModelScope.launch {
              when (val result = useCases.getAllEntityUseCase()) {
                  is Resource.Error -> {
                      _state.value = BasketState.Error(result.errorMessage)
                  }
                  is Resource.Loading -> {
                      _state.value = BasketState.Loading
                  }
                  is Resource.Success -> {
                      result.data.collect {
                        _state.value =  BasketState.Success(it)
                      }


                  }
              }
          /*  when (useCases.getAllEntityUseCase()) {
                is Resource.Error -> {
                    val message = (useCases.getAllEntityUseCase() as Resource.Error).errorMessage
                    _state.value = BasketState.Error(message)
                }
                is Resource.Loading -> {
                    _state.value = BasketState.Loading
                }
                is Resource.Success -> {
                    (useCases.getAllEntityUseCase() as Resource.Success<Flow<List<BasketEntity>>>)
                        .data.collectLatest {
                            _state.value = BasketState.Success(it)
                        }
                }
            }*/
        }
    }

    fun deleteEntityFromRoom(entity: BasketEntity) {
        viewModelScope.launch {
            useCases.deleteEntityUseCase.invoke(entity = entity)
           // getEntityFromRoom()
        }
    }

    fun updateEntityFromRoom(entity: BasketEntity) {
        viewModelScope.launch {
            useCases.updateEntityUseCase(entity = entity)
            //getEntityFromRoom()
        }
    }

    fun deleteAllEntityFromRoom() {
        viewModelScope.launch {
            useCases.deleteAllEntityUseCase()
           // getEntityFromRoom()
        }
    }

}
