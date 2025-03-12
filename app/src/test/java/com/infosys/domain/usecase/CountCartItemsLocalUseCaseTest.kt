package com.infosys.domain.usecase

import com.infosys.data.remote.Resource
import com.infosys.domain.repository.CountCartItemsLocalRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
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
class CountCartItemsLocalUseCaseTest {

    private lateinit var useCase: CountCartItemsLocalUseCase
    private val repository: CountCartItemsLocalRepository = mockk<CountCartItemsLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = CountCartItemsLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count cart items return SUCCESS`() = runTest {
        coEvery { repository.getCartListCount() } returns flowOf(
            Resource.Success(1))

        val result = useCase.getCartListCount()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count cart items return Error`() = runTest {
        coEvery { repository.getCartListCount() } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getCartListCount()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }
    
}