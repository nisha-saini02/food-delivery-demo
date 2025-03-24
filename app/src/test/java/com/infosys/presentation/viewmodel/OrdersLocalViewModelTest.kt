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
        val expected = listOf(Order())

        `when`(deleteAllCartsLocalUseCase.deleteAllCarts())
            .thenReturn(expected.size)

        viewModel.deleteAllCarts()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.deleteAllCarts.value == expected.size)
    }

    @Test
    fun deleteAllCarts_failure() = runTest {
        val expected = null

        `when`(deleteAllCartsLocalUseCase.deleteAllCarts())
            .thenReturn(expected)

        viewModel.deleteAllCarts()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.deleteAllCarts.value == expected)
    }

    @Test
    fun deleteAllCarts_exception() = runTest {
        val expected = null

        `when`(deleteAllCartsLocalUseCase.deleteAllCarts())
            .thenThrow(RuntimeException::class.java)

        viewModel.deleteAllCarts()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.deleteAllCarts.value == expected)
    }

    @Test
    fun insertOrderItem_success() = runTest {
        val expected = Order()

        `when`(insertOrderItemLocalUseCase.insertItem(expected))
            .thenReturn(1)

        viewModel.insertOrderItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.insertOrder.value == 1L)
    }

    @Test
    fun insertOrderItem_failure() = runTest {
        val expected = Order()

        `when`(insertOrderItemLocalUseCase.insertItem(expected))
            .thenReturn(-1)

        viewModel.insertOrderItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.insertOrder.value == -1L)
    }

    @Test
    fun insertOrderItem_exception() = runTest {
        val expected = Order()

        `when`(insertOrderItemLocalUseCase.insertItem(expected))
            .thenThrow(RuntimeException::class.java)

        viewModel.insertOrderItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.insertOrder.value == -1L)
    }

    @Test
    fun getOrder_success() = runTest {
        val expected = Order()

        `when`(fetchOrderLocalUserCase.getOrder(""))
            .thenReturn(expected)

        viewModel.getOrder("")
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.fetchOrder.value == expected)
    }

    @Test
    fun getOrder_failure() = runTest {
        val expected = null

        `when`(fetchOrderLocalUserCase.getOrder(""))
            .thenReturn(expected)

        viewModel.getOrder("")
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.fetchOrder.value == expected)
    }

    @Test
    fun getOrder_exception() = runTest {
        `when`(fetchOrderLocalUserCase.getOrder(""))
            .thenThrow(RuntimeException::class.java)

        viewModel.getOrder("")
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.fetchOrder.value == null)
    }
}