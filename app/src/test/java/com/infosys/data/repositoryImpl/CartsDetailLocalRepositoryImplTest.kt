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

class CartsDetailLocalRepositoryImplTest {

    private lateinit var repository: CartsDetailLocalRepositoryImpl
    private val dao = mock(CartDao::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = CartsDetailLocalRepositoryImpl(dao)
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
        `when`(dao.getAll())
            .thenReturn(mutableListOf())

        val result = repository.fetchAllItems()
        advanceUntilIdle()

        TestCase.assertEquals(mutableListOf<Cart>(), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem_failure() = runTest {
        `when`(dao.getAll())
            .thenThrow(RuntimeException("test"))

        val result = repository.fetchAllItems()
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cartCount_success() = runTest {
        `when`(dao.getCartListCount())
            .thenReturn(5)

        val result = repository.getCartListCount()
        advanceUntilIdle()

        TestCase.assertEquals(5, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cartCount_failure() = runTest {
        `when`(dao.getCartListCount())
            .thenThrow(RuntimeException("test"))

        val result = repository.getCartListCount()
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cartGrandTotalCount_success() = runTest {
        `when`(dao.getCartGrandSum())
            .thenReturn(500f)

        val result = repository.getCartListGrandTotalCount()
        advanceUntilIdle()

        TestCase.assertEquals(500f, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cartGrandTotalCount_failure() = runTest {
        `when`(dao.getCartGrandSum())
            .thenThrow(RuntimeException("test"))

        val result = repository.getCartListGrandTotalCount()
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteAllCarts_success() = runTest {
        `when`(dao.deleteAllCarts())
            .thenReturn(3)

        val result = repository.deleteAllCarts()
        advanceUntilIdle()

        TestCase.assertEquals(3, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteAllCarts_failure() = runTest {
        `when`(dao.deleteAllCarts())
            .thenThrow(RuntimeException("test"))

        val result = repository.deleteAllCarts()
        advanceUntilIdle()

        TestCase.assertEquals(null, result)
    }

}