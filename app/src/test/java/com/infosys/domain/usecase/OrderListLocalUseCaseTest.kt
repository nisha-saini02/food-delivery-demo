package com.infosys.domain.usecase

import com.infosys.data.remote.Resource
import com.infosys.domain.repository.OrderListLocalRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OrderListLocalUseCaseTest {

    private lateinit var useCase: OrderListLocalUseCase
    @Mock
    private lateinit var repository: OrderListLocalRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = OrderListLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch orders return SUCCESS`() = runTest {
        `when`(
            repository.orderList()
        ).thenReturn(flowOf(Resource.Success(listOf())))

        val result = useCase.orderList()
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }

        verify(repository).orderList()
    }

    @Test
    fun `fetch orders return Error`() = runTest {
        `when`(
            repository.orderList()
        ).thenReturn(flowOf(Resource.Error("Test Error")))

        val result = useCase.orderList()
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }

        verify(repository).orderList()
    }
    
}