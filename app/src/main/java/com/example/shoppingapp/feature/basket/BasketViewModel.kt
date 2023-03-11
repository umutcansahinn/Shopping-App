package com.example.shoppingapp.feature.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.use_case.delete_all_entity.DeleteAllEntityUseCase
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCase
import com.example.shoppingapp.core.domain.use_case.get_all_entity.GetAllEntityUseCase
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllEntityUseCase: GetAllEntityUseCase,
    private val deleteEntityUseCase: DeleteEntityUseCase,
    private val updateEntityUseCase: UpdateEntityUseCase,
    private val deleteAllEntityUseCase: DeleteAllEntityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<Flow<List<BasketEntity>>>>(Resource.Loading)
    val state get() = _state.asStateFlow()

    fun getEntityFromRoom() {
        viewModelScope.launch {
              _state.value = getAllEntityUseCase()
        }
    }

    fun deleteEntityFromRoom(entity: BasketEntity) {
        viewModelScope.launch {
            deleteEntityUseCase.invoke(entity = entity)
        }
    }

    fun updateEntityFromRoom(entity: BasketEntity) {
        viewModelScope.launch {
            updateEntityUseCase(entity = entity)
        }
    }

    fun deleteAllEntityFromRoom() {
        viewModelScope.launch {
            deleteAllEntityUseCase()
        }
    }
}
