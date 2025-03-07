package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.order.Order
import com.infosys.data.model.usecase.LocalUseCase
import com.infosys.data.remote.Resource
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

    private var _countCartItems = MutableStateFlow<Resource<Int?>>(Resource.Loading())
    val countCartItems: StateFlow<Resource<Int?>> = _countCartItems

    private var _grandTotalCartItems = MutableStateFlow<Resource<Float?>>(Resource.Loading())
    val grandTotalCartItems: StateFlow<Resource<Float?>> = _grandTotalCartItems

    private var _deleteAllCarts = MutableStateFlow<Resource<Int>>(Resource.Loading())
    val deleteAllCarts: StateFlow<Resource<Int>> = _deleteAllCarts

    private var _orders = MutableStateFlow<Resource<List<Order>>>(Resource.Loading())
    val orders: StateFlow<Resource<List<Order>>> = _orders

    private var _insertOrder = MutableStateFlow<Resource<Long>>(Resource.Loading())
    val insertOrder: StateFlow<Resource<Long>> = _insertOrder

    private var _fetchOrder = MutableStateFlow<Resource<Order?>>(Resource.Loading())
    val fetchOrder: StateFlow<Resource<Order?>> = _fetchOrder

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
    fun resetDeleteObserver() {
        _deleteItem.value = Resource.Success(0)
    }

    fun countCartItems() {
        viewModelScope.launch {
            _countCartItems.value= Resource.Loading()
            if (_countCartItems.value.data == null)
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
                    .onStart {  }
                    .catch {
                        _countCartItems.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        _countCartItems.value = Resource.Success(it)
                    }
        }
    }

    fun grandTotalCartItems() {
        viewModelScope.launch {
            _grandTotalCartItems.value= Resource.Loading()
            if (_grandTotalCartItems.value.data == null)
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
                    .onStart {  }
                    .catch {
                        _grandTotalCartItems.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        _grandTotalCartItems.value = Resource.Success(it)
                    }
        }
    }

    fun deleteAllCarts() {
        viewModelScope.launch {
            _deleteAllCarts.value= Resource.Loading()
            if (_deleteAllCarts.value.data == null)
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
                    .onStart {  }
                    .catch {
                        _deleteAllCarts.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        _deleteAllCarts.value = Resource.Success(it)
                    }
        }
    }

    fun orderList() {
        viewModelScope.launch {
            _orders.value= Resource.Loading()
            localUseCase.orderListLocalUseCase.orderList()
                .onStart {  }
                .catch {
                    _orders.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _orders.value = Resource.Success(it)
                }
        }
    }

    fun insertOrderItem(order: Order) {
        viewModelScope.launch {
            _insertOrder.value= Resource.Loading()
            localUseCase.insertOrderItemLocalUseCase.insertItem(order)
                .onStart {  }
                .catch {
                    _insertOrder.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _insertOrder.value = Resource.Success(it)
                }
        }
    }

    fun getOrder(orderId: String) {
        viewModelScope.launch {
            _fetchOrder.value= Resource.Loading()
            localUseCase.fetchOrderLocalUserCase.getOrder(orderId)
                .onStart {  }
                .catch {
                    _fetchOrder.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _fetchOrder.value = Resource.Success(it)
                }
        }
    }
}