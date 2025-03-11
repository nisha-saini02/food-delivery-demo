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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LocalViewModel @Inject constructor(
    val localUseCase: LocalUseCase,
): ViewModel() {
    private var _cart = MutableStateFlow<Resource<List<Cart>>>(Resource.Loading())
    val cart: StateFlow<Resource<List<Cart>>> = _cart

    private var _cartItem = MutableStateFlow<Resource<Cart?>>(Resource.Loading())
    val cartItem: StateFlow<Resource<Cart?>> = _cartItem

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
            try {
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
                    .collect {
                        _cart.value = it
                    }
            } catch (e: Exception) {
                _cart.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCartItem(itemId: String) {
        viewModelScope.launch {
            _cartItem.value= Resource.Loading()
            try {
                localUseCase.fetchCartItemLocalUseCase.fetchItem(itemId)
                    .collect {
                        _cartItem.value = it
                    }
            } catch (e: Exception) {
                _cartItem.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun insertItem(cart: Cart) {
        viewModelScope.launch {
            _insertItem.value= Resource.Loading()
            try {
                localUseCase.insertCartItemLocalUseCase.insertItem(cart)
                    .collect {
                        _insertItem.value = it
                    }
            } catch (e: Exception) {
                _insertItem.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun updateItem(cart: Cart) {
        viewModelScope.launch {
            _updateItem.value= Resource.Loading()
            try {
                localUseCase.updateCartItemLocalUseCase.updateItem(cart)
                    .collect {
                        _updateItem.value = it
                    }
            } catch (e: Exception) {
                _updateItem.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun deleteItem(cart: Cart) {
        viewModelScope.launch {
            _deleteItem.value= Resource.Loading()
            try {
                localUseCase.deleteCartItemLocalUseCase.delete(cart)
                    .collect {
                        _deleteItem.value = it
                    }
            } catch (e: Exception) {
                _deleteItem.value = Resource.Error(e.message.toString())
            }
        }
    }
    fun resetDeleteObserver() {
        _deleteItem.value = Resource.Success(0)
    }

    fun countCartItems() {
        viewModelScope.launch {
            _countCartItems.value= Resource.Loading()
            try {
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
                    .collect {
                        _countCartItems.value = it
                    }
            } catch (e: Exception) {
                _countCartItems.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun grandTotalCartItems() {
        viewModelScope.launch {
            _grandTotalCartItems.value= Resource.Loading()
            try {
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
                    .collect {
                        _grandTotalCartItems.value = it
                    }
            } catch (e: Exception) {
                _grandTotalCartItems.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun deleteAllCarts() {
        viewModelScope.launch {
            _deleteAllCarts.value= Resource.Loading()
            try {
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
                    .collect {
                        _deleteAllCarts.value = it
                    }
            } catch (e: Exception) {
                _deleteAllCarts.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun orderList() {
        viewModelScope.launch {
            _orders.value= Resource.Loading()
            try {
                localUseCase.orderListLocalUseCase.orderList()
                    .collect {
                        _orders.value = it
                    }
            } catch (e: Exception) {
                _orders.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun insertOrderItem(order: Order) {
        viewModelScope.launch {
            _insertOrder.value= Resource.Loading()
            try {
                localUseCase.insertOrderItemLocalUseCase.insertItem(order)
                    .collect {
                        _insertOrder.value = it
                    }
            } catch (e: Exception) {
                _insertOrder.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getOrder(orderId: String) {
        viewModelScope.launch {
            _fetchOrder.value= Resource.Loading()
            try {
                localUseCase.fetchOrderLocalUserCase.getOrder(orderId)
                    .collect {
                        _fetchOrder.value = it
                    }
            } catch (e: Exception) {
                _fetchOrder.value = Resource.Error(e.message.toString())
            }
        }
    }
}