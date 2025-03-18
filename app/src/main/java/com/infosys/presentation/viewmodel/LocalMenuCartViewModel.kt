package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.cart.Cart
import com.infosys.domain.usecase.DeleteCartLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
import com.infosys.domain.usecase.UpdateCartItemLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalMenuCartViewModel  @Inject constructor(
    private var insertCartItemLocalUseCase: InsertCartItemLocalUseCase,
    private var updateCartItemLocalUseCase: UpdateCartItemLocalUseCase,
    private var deleteCartItemLocalUseCase: DeleteCartLocalUseCase,
): ViewModel() {
    private var _insertItem = MutableStateFlow<Long>(0)
    val insertItem: StateFlow<Long> = _insertItem

    private var _updateItem = MutableStateFlow<Int>(0)
    val updateItem: StateFlow<Int> = _updateItem

    private var _deleteItem = MutableStateFlow<Int>(0)
    val deleteItem: StateFlow<Int> = _deleteItem

    fun insertItem(cart: Cart) {
        viewModelScope.launch {
            try {
                _insertItem.value = insertCartItemLocalUseCase.insertItem(cart)
            } catch (e: Exception) {
                _insertItem.value = 0
            }
        }
    }

    fun updateItem(cart: Cart) {
        viewModelScope.launch {
            try {
                _updateItem.value = updateCartItemLocalUseCase.updateItem(cart)
            } catch (e: Exception) {
                _updateItem.value = 0
            }
        }
    }

    fun deleteItem(cart: Cart) {
        viewModelScope.launch {
            try {
                _deleteItem.value = deleteCartItemLocalUseCase.delete(cart)
            } catch (e: Exception) {
                _deleteItem.value = 0
            }
        }
    }
    fun resetDeleteObserver() {
        _deleteItem.value = 0
    }
}