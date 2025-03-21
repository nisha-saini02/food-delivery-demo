package com.infosys.presentation.viewmodel

import com.infosys.data.model.cart.Cart
import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.CountCartItemsLocalUseCase
import com.infosys.domain.usecase.GrandTotalCartItemsLocalUseCase
import junit.framework.TestCase.assertEquals
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

class LocalCartViewModelTest {

    private lateinit var viewModel: LocalCartViewModel
    private var allCartItemsLocalUseCase = mock(AllCartItemsLocalUseCase::class.java)
    private var countCartItemsLocalUseCase = mock(CountCartItemsLocalUseCase::class.java)
    private var grandTotalCartItemsLocalUseCase = mock(GrandTotalCartItemsLocalUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = LocalCartViewModel(allCartItemsLocalUseCase, countCartItemsLocalUseCase, grandTotalCartItemsLocalUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCart_success() = runTest {
        val expected = emptyList<Cart>()

        `when`(allCartItemsLocalUseCase.fetchAllItems())
            .thenReturn(expected)

        viewModel.getAllCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.cart.value)
    }

    @Test
    fun getCart_failure() = runTest {
        val expected = null

        `when`(allCartItemsLocalUseCase.fetchAllItems())
            .thenReturn(expected)

        viewModel.getAllCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.cart.value)
    }

    @Test
    fun getCart_exception() = runTest {
        `when`(allCartItemsLocalUseCase.fetchAllItems())
            .thenThrow(RuntimeException::class.java)

        viewModel.getAllCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.cart.value)
    }

    @Test
    fun countCartItems_success() = runTest {
        val expected = 50

        `when`(countCartItemsLocalUseCase.getCartListCount())
            .thenReturn(expected)

        viewModel.countCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.countCartItems.value)
    }

    @Test
    fun countCartItems_exception() = runTest {
        `when`(countCartItemsLocalUseCase.getCartListCount())
            .thenThrow(RuntimeException::class.java)

        viewModel.countCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.countCartItems.value)
    }

    @Test
    fun grandTotalCartItems_success() = runTest {
        val expected = 20f

        `when`(grandTotalCartItemsLocalUseCase.getCartGrandSum())
            .thenReturn(expected)

        viewModel.grandTotalCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.grandTotalCartItems.value)
    }

    @Test
    fun grandTotalCartItems_exception() = runTest {
        `when`(grandTotalCartItemsLocalUseCase.getCartGrandSum())
            .thenThrow(RuntimeException::class.java)

        viewModel.grandTotalCartItems()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.grandTotalCartItems.value)
    }
}