package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
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

class MenuCartLocalRepositoryImplTest {

    private lateinit var repository: MenuCartLocalRepositoryImpl
    private val dao = mock(CartDao::class.java)
    private val cart = Cart()
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = MenuCartLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_success() = runTest {
        `when`(dao.insertItem(cart))
            .thenReturn(1)

        val result = repository.insertItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_failure() = runTest {
        `when`(dao.insertItem(cart))
            .thenReturn(-1)

        val result = repository.insertItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_exception() = runTest {
        `when`(dao.insertItem(cart))
            .thenThrow(RuntimeException("test"))

        val result = repository.insertItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateItem_success() = runTest {
        `when`(dao.updateItem(cart))
            .thenReturn(1)

        val result = repository.updateItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateItem_failure() = runTest {
        `when`(dao.updateItem(cart))
            .thenReturn(-1)

        val result = repository.updateItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateItem_exception() = runTest {
        `when`(dao.updateItem(cart))
            .thenThrow(RuntimeException("test"))

        val result = repository.updateItem(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete_success() = runTest {
        `when`(dao.delete(cart))
            .thenReturn(1)

        val result = repository.delete(cart)
        advanceUntilIdle()

        TestCase.assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete_failure() = runTest {
        `when`(dao.delete(cart))
            .thenReturn(-1)

        val result = repository.delete(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete_exception() = runTest {
        `when`(dao.delete(cart))
            .thenThrow(RuntimeException("test"))

        val result = repository.delete(cart)
        advanceUntilIdle()

        TestCase.assertEquals(-1, result)
    }
}