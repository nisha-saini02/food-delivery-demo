package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
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
class FetchOrderLocalRepositoryImplTest {

    private lateinit var repository: FetchOrderLocalRepositoryImpl
    private val dao = mockk<OrderDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = FetchOrderLocalRepositoryImpl(dao)
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
        coEvery { dao.getOrder("") } returns Order()

        val result = repository.getOrder("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders throw EXCEPTION`() = runTest {
        coEvery { dao.getOrder("") } throws Exception("Test Error")

        val result = repository.getOrder("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}