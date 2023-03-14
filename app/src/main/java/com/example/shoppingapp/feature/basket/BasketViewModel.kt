package com.example.shoppingapp.feature.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.common.Resource
import com.example.shoppingapp.core.data.source.local.BasketEntity
import com.example.shoppingapp.core.domain.use_case.delete_entity.DeleteEntityUseCase
import com.example.shoppingapp.core.domain.use_case.get_all_entities.GetAllEntitiesUseCase
import com.example.shoppingapp.core.domain.use_case.update_entity.UpdateEntityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllEntitiesUseCase: GetAllEntitiesUseCase,
    private val deleteEntityUseCase: DeleteEntityUseCase,
    private val updateEntityUseCase: UpdateEntityUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<Flow<List<BasketEntity>>>>(Resource.Loading)
    val state get() = _state.asStateFlow()

    fun getEntityFromRoom() {
        viewModelScope.launch {
              _state.value = getAllEntitiesUseCase()
        }
    }

    fun deleteEntityFromRoom(entity: BasketEntity) {
        viewModelScope.launch {
            deleteEntityUseCase.invoke(entity = entity)
        }
    }

    fun updateEntityFromRoom(entity: BasketEntity,isPlus: Boolean) {
        viewModelScope.launch {
            if (isPlus) {
                updateEntityUseCase(entity = entity.copy(itemCount = entity.itemCount +1))
            }else {
                if (entity.itemCount == 1) {
                    deleteEntityFromRoom(entity = entity)
                } else {
                    updateEntityUseCase(entity = entity.copy(itemCount = entity.itemCount -1))
                }
            }
        }
    }
}
