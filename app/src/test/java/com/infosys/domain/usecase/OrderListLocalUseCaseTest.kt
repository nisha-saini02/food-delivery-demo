package com.infosys.domain.usecase

import com.infosys.data.remote.Resource
import com.infosys.domain.repository.OrderListLocalRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class OrderListLocalUseCaseTest {

    private lateinit var useCase: OrderListLocalUseCase
    private val repository: OrderListLocalRepository = mockk<OrderListLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = OrderListLocalUseCase(repository)
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
        coEvery { repository.orderList() } returns flowOf(
            Resource.Success(listOf())
        )

        val result = useCase.orderList()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders return Error`() = runTest {
        coEvery { repository.orderList() } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.orderList()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}