package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
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
class DeleteAllCartsLocalRepositoryImplTest {

    private lateinit var repository: DeleteAllCartsLocalRepositoryImpl
    private val dao = mockk<CartDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = DeleteAllCartsLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete all carts return SUCCESS`() = runTest {
        coEvery { dao.deleteAllCarts() } returns 1

        val result = repository.deleteAllCarts()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete all carts throw EXCEPTION`() = runTest {
        coEvery { dao.deleteAllCarts() } throws Exception("Test Error")

        val result = repository.deleteAllCarts()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

}