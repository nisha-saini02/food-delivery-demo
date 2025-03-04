package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.usecase.LocalUseCase
import com.infosys.data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LocalViewModel @Inject constructor(
    val localUseCase: LocalUseCase,
): ViewModel() {
    private var _cart = MutableStateFlow<Resource<List<Cart>?>>(Resource.Loading())
    val cart: StateFlow<Resource<List<Cart>?>> = _cart

    private var _cartItem = MutableStateFlow<Resource<Cart>>(Resource.Loading())
    val cartItem: StateFlow<Resource<Cart>> = _cartItem

    private var _insertItem = MutableStateFlow<Resource<Long>>(Resource.Loading())
    val insertItem: StateFlow<Resource<Long>> = _insertItem

    private var _updateItem = MutableStateFlow<Resource<Int>>(Resource.Loading())
    val updateItem: StateFlow<Resource<Int>> = _updateItem

    private var _deleteItem = MutableStateFlow<Resource<Int>>(Resource.Loading())
    val deleteItem: StateFlow<Resource<Int>> = _deleteItem

    fun getAllCartItems() {
        viewModelScope.launch {
            _cart.value= Resource.Loading()
            if (_cart.value.data.isNullOrEmpty())
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
                    .onStart {  }
                    .catch {
                        _cart.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        _cart.value = Resource.Success(it)
                    }
        }
    }

    fun getCartItem(itemId: String) {
        viewModelScope.launch {
            _cartItem.value= Resource.Loading()
            if (_cartItem.value.data == null)
                localUseCase.fetchCartItemLocalUseCase.fetchItem(itemId)
                    .onStart {  }
                    .catch {
                        _cartItem.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        if (it != null)
                            _cartItem.value = Resource.Success(it)
                        else
                            _cartItem.value = Resource.Error("data not present")
                    }
        }
    }

    fun insertItem(cart: Cart) {
        viewModelScope.launch {
            _insertItem.value= Resource.Loading()
            if (_insertItem.value.data == null)
                localUseCase.insertCartItemLocalUseCase.insertItem(cart)
                    .onStart {  }
                    .catch {
                        _insertItem.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        if (it >= 1L)
                            _insertItem.value = Resource.Success(it)
                        else
                            _insertItem.value = Resource.Error("data not present")
                    }
        }
    }

    fun updateItem(cart: Cart) {
        viewModelScope.launch {
            _updateItem.value= Resource.Loading()
            if (_updateItem.value.data == null)
                localUseCase.updateCartItemLocalUseCase.updateItem(cart)
                    .onStart {  }
                    .catch {
                        _updateItem.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        if (it >= 1L)
                            _updateItem.value = Resource.Success(it)
                        else
                            _updateItem.value = Resource.Error("data not present")
                    }
        }
    }

    fun deleteItem(cart: Cart) {
        viewModelScope.launch {
            _deleteItem.value= Resource.Loading()
            if (_deleteItem.value.data == null)
                localUseCase.deleteCartItemLocalUseCase.delete(cart)
                    .onStart {  }
                    .catch {
                        _deleteItem.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        if (it >= 1L)
                            _deleteItem.value = Resource.Success(it)
                        else
                            _deleteItem.value = Resource.Error("data not present")
                    }
        }
    }
}