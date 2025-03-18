package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.order.Order
import com.infosys.domain.usecase.DeleteAllCartsLocalUseCase
import com.infosys.domain.usecase.FetchOrderLocalUserCase
import com.infosys.domain.usecase.InsertOrderItemLocalUseCase
import com.infosys.domain.usecase.OrderListLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersLocalViewModel @Inject constructor(
    private val deleteAllCartsLocalUseCase: DeleteAllCartsLocalUseCase,
    private val orderListLocalUseCase: OrderListLocalUseCase,
    private val insertOrderItemLocalUseCase: InsertOrderItemLocalUseCase,
    private val fetchOrderLocalUserCase: FetchOrderLocalUserCase,
): ViewModel() {
    private var _deleteAllCarts = MutableStateFlow<Int?>(null)
    val deleteAllCarts: StateFlow<Int?> = _deleteAllCarts

    private var _orders = MutableStateFlow<List<Order>?>(null)
    val orders: StateFlow<List<Order>?> = _orders

    private var _insertOrder = MutableStateFlow<Long?>(null)
    val insertOrder: StateFlow<Long?> = _insertOrder

    private var _fetchOrder = MutableStateFlow<Order?>(null)
    val fetchOrder: StateFlow<Order?> = _fetchOrder

    fun deleteAllCarts() {
        viewModelScope.launch {
            try {
                _deleteAllCarts.value = deleteAllCartsLocalUseCase.deleteAllCarts()
            } catch (e: Exception) {
                _deleteAllCarts.value = null
            }
        }
    }

    fun orderList() {
        viewModelScope.launch {
            try {
                _orders.value = orderListLocalUseCase.orderList()
            } catch (e: Exception) {
                _orders.value = null
            }
        }
    }

    fun insertOrderItem(order: Order) {
        viewModelScope.launch {
            try {
                _insertOrder.value = insertOrderItemLocalUseCase.insertItem(order)
            } catch (e: Exception) {
                _insertOrder.value = -1
            }
        }
    }

    fun getOrder(orderId: String) {
        viewModelScope.launch {
            try {
                _fetchOrder.value = fetchOrderLocalUserCase.getOrder(orderId)
            } catch (e: Exception) {
                _fetchOrder.value = null
            }
        }
    }
}