package com.example.shoppingapp.feature.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.core.domain.use_case.delete_all_entities.DeleteAllEntitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val deleteAllEntitiesUseCase: DeleteAllEntitiesUseCase
): ViewModel() {


    fun deleteAllEntityFromRoom() {
        viewModelScope.launch {
            deleteAllEntitiesUseCase()
        }
    }
}