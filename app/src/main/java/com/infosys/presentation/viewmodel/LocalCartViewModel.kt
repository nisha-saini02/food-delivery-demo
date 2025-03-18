package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.cart.Cart
import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.CountCartItemsLocalUseCase
import com.infosys.domain.usecase.GrandTotalCartItemsLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalCartViewModel  @Inject constructor(
    private var allCartItemsLocalUseCase: AllCartItemsLocalUseCase,
    private var countCartItemsLocalUseCase: CountCartItemsLocalUseCase,
    private var grandTotalCartItemsLocalUseCase: GrandTotalCartItemsLocalUseCase,
): ViewModel() {

    private var _cart = MutableStateFlow<List<Cart>?>(emptyList())
    val cart: StateFlow<List<Cart>?> = _cart

    private var _countCartItems = MutableStateFlow<Int?>(null)
    val countCartItems: StateFlow<Int?> = _countCartItems

    private var _grandTotalCartItems = MutableStateFlow<Float?>(null)
    val grandTotalCartItems: StateFlow<Float?> = _grandTotalCartItems

    fun getAllCartItems() {
        viewModelScope.launch {
            try {
                _cart.value = allCartItemsLocalUseCase.fetchAllItems()
            } catch (e: Exception) {
                _cart.value = null
            }
        }
    }

    fun countCartItems() {
        viewModelScope.launch {
            try {
                _countCartItems.value = countCartItemsLocalUseCase.getCartListCount()
            } catch (e: Exception) {
                _countCartItems.value = null
            }
        }
    }

    fun grandTotalCartItems() {
        viewModelScope.launch {
            try {
                _grandTotalCartItems.value = grandTotalCartItemsLocalUseCase.getCartGrandSum()
            } catch (e: Exception) {
                _grandTotalCartItems.value = null
            }
        }
    }
}