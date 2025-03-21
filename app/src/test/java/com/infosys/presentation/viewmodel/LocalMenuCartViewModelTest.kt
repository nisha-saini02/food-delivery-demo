package com.infosys.presentation.viewmodel

import com.infosys.data.model.cart.Cart
import com.infosys.domain.usecase.DeleteCartLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
import com.infosys.domain.usecase.UpdateCartItemLocalUseCase
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

class LocalMenuCartViewModelTest {

    private lateinit var viewModel: LocalMenuCartViewModel
    private var insertCartItemLocalUseCase = mock(InsertCartItemLocalUseCase::class.java)
    private var updateCartItemLocalUseCase = mock(UpdateCartItemLocalUseCase::class.java)
    private var deleteCartItemLocalUseCase = mock(DeleteCartLocalUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = LocalMenuCartViewModel(insertCartItemLocalUseCase, updateCartItemLocalUseCase, deleteCartItemLocalUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun insert_success() = runTest {
        val expected = Cart()
        `when`(insertCartItemLocalUseCase.insertItem(expected))
            .thenReturn(1)

        viewModel.insertItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.insertItem.value)
    }

    @Test
    fun insert_exception() = runTest {
        val expected = Cart()
        `when`(insertCartItemLocalUseCase.insertItem(expected))
            .thenThrow(RuntimeException::class.java)

        viewModel.insertItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(0, viewModel.insertItem.value)
    }

    @Test
    fun update_success() = runTest {
        val expected = Cart()
        `when`(updateCartItemLocalUseCase.updateItem(expected))
            .thenReturn(10)

        viewModel.updateItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(10, viewModel.updateItem.value)
    }

    @Test
    fun update_exception() = runTest {
        val expected = Cart()
        `when`(updateCartItemLocalUseCase.updateItem(expected))
            .thenThrow(RuntimeException::class.java)

        viewModel.updateItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(0, viewModel.updateItem.value)
    }

    @Test
    fun delete_success() = runTest {
        val expected = Cart()
        `when`(deleteCartItemLocalUseCase.delete(expected))
            .thenReturn(1)

        viewModel.deleteItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.deleteItem.value)
    }

    @Test
    fun delete_exception() = runTest {
        val expected = Cart()
        `when`(deleteCartItemLocalUseCase.delete(expected))
            .thenThrow(RuntimeException::class.java)

        viewModel.deleteItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(0, viewModel.deleteItem.value)
    }

    @Test
    fun resetDeleteObserver() {
        viewModel.resetDeleteObserver()
        assertEquals(0, viewModel.deleteItem.value)
    }
}