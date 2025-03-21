package com.infosys.presentation.viewmodel

import com.infosys.data.model.order.Order
import com.infosys.domain.usecase.DeleteAllCartsLocalUseCase
import com.infosys.domain.usecase.FetchOrderLocalUserCase
import com.infosys.domain.usecase.InsertOrderItemLocalUseCase
import com.infosys.domain.usecase.OrderListLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class OrdersLocalViewModelTest {

    private lateinit var viewModel: OrdersLocalViewModel
    private val deleteAllCartsLocalUseCase = mock(DeleteAllCartsLocalUseCase::class.java)
    private val orderListLocalUseCase = mock(OrderListLocalUseCase::class.java)
    private val insertOrderItemLocalUseCase = mock(InsertOrderItemLocalUseCase::class.java)
    private val fetchOrderLocalUserCase = mock(FetchOrderLocalUserCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = OrdersLocalViewModel(deleteAllCartsLocalUseCase, orderListLocalUseCase, insertOrderItemLocalUseCase, fetchOrderLocalUserCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getOrders_success() = runTest {
        val expected = listOf(Order())

        `when`(orderListLocalUseCase.orderList())
            .thenReturn(expected)

        viewModel.orderList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.orders.value == expected)
    }

    @Test
    fun getOrders_failure() = runTest {
        val expected = null

        `when`(orderListLocalUseCase.orderList())
            .thenReturn(expected)

        viewModel.orderList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.orders.value == expected)
    }

    @Test
    fun getOrders_exception() = runTest {
        val expected = null

        `when`(orderListLocalUseCase.orderList())
            .thenThrow(RuntimeException::class.java)

        viewModel.orderList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.orders.value == expected)
    }

    @Test
    fun deleteAllCarts_success() = runTest {
    }

    @Test
    fun insertOrderItem_success() = runTest {
    }

    @Test
    fun getOrder_success() = runTest {
    }
}