package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class OrdersLocalRepositoryImplTest{

    private lateinit var repository: OrdersLocalRepositoryImpl
    private val dao = mock(OrderDao::class.java)
    private val order = Order()
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = OrdersLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun orderList_success() = runTest {
        `when`(dao.getAll())
            .thenReturn(mutableListOf())

        val result = repository.orderList()
        advanceUntilIdle()

        TestCase.assertEquals(mutableListOf<Order>(), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun orderList_exception() = runTest {
        `when`(dao.getAll())
            .thenThrow(RuntimeException("test"))

        val result = repository.orderList()
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getOrder_success() = runTest {
        `when`(dao.getOrder("order"))
            .thenReturn(order)

        val result = repository.getOrder("order")
        advanceUntilIdle()

        TestCase.assertEquals(order, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getOrder_exception() = runTest {
        `when`(dao.getOrder("order"))
            .thenThrow(RuntimeException("test"))

        val result = repository.getOrder("order")
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_success() = runTest {
        `when`(dao.insertItem(order))
            .thenReturn(1)

        val result = repository.insertItem(order)
        advanceUntilIdle()

        TestCase.assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_exception() = runTest {
        `when`(dao.insertItem(order))
            .thenThrow(RuntimeException("test"))

        val result = repository.insertItem(order)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }
}