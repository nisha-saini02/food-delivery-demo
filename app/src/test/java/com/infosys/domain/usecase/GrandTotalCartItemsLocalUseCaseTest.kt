package com.infosys.domain.usecase

import com.infosys.data.remote.Resource
import com.infosys.domain.repository.GrandTotalCartItemsLocalRepository
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
class GrandTotalCartItemsLocalUseCaseTest {

    private lateinit var useCase: GrandTotalCartItemsLocalUseCase
    @Mock
    private lateinit var repository: GrandTotalCartItemsLocalRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = GrandTotalCartItemsLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `grand total cart items return SUCCESS`() = runTest {
        `when`(
            repository.getCartListGrandTotalCount()
        ).thenReturn(flowOf(
            Resource.Success(1f)))

        val result = useCase.getCartGrandSum()
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }

        verify(repository).getCartListGrandTotalCount()
    }

    @Test
    fun `grand total cart items return Error`() = runTest {
        `when`(
            repository.getCartListGrandTotalCount()
        ).thenReturn(flowOf(
            Resource.Error("Test Error")))

        val result = useCase.getCartGrandSum()
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }

        verify(repository).getCartListGrandTotalCount()
    }
    
}