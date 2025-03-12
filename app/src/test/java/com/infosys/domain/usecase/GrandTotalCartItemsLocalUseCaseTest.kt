package com.infosys.domain.usecase

import com.infosys.data.remote.Resource
import com.infosys.domain.repository.GrandTotalCartItemsLocalRepository
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
class GrandTotalCartItemsLocalUseCaseTest {

    private lateinit var useCase: GrandTotalCartItemsLocalUseCase
    private val repository: GrandTotalCartItemsLocalRepository = mockk<GrandTotalCartItemsLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = GrandTotalCartItemsLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `grand total cart items return SUCCESS`() = runTest {
        coEvery { repository.getCartListGrandTotalCount() } returns flowOf(
            Resource.Success(1f)
        )

        val result = useCase.getCartGrandSum()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `grand total cart items return Error`() = runTest {
        coEvery { repository.getCartListGrandTotalCount() } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getCartGrandSum()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }
    
}