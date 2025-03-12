package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
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
class OrderListLocalRepositoryImplTest {

    private lateinit var repository: OrderListLocalRepositoryImpl
    private val dao = mockk<OrderDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = OrderListLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders return SUCCESS`() = runTest {
        coEvery { dao.getAll() } returns emptyList()

        val result = repository.orderList()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders throw EXCEPTION`() = runTest {
        coEvery { dao.getAll() } throws Exception("Test Error")

        val result = repository.orderList()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }

}