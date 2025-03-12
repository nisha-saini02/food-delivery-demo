package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
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
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpdateCartItemLocalRepositoryImplTest {

    private lateinit var repository: UpdateCartItemLocalRepositoryImpl
    private val dao = mockk<CartDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = UpdateCartItemLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item return SUCCESS`() = runTest {
        coEvery { dao.updateItem(Cart()) } returns 1

        val result = repository.updateItem(Cart())
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item throw EXCEPTION`() = runTest {
        coEvery { dao.updateItem(Cart()) } throws Exception("Test Error")

        val result = repository.updateItem(Cart())
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

}