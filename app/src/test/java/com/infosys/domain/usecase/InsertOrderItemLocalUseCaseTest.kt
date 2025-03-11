package com.infosys.domain.usecase

import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.InsertOrderItemLocalRepository
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
class InsertOrderItemLocalUseCaseTest {

    private lateinit var useCase: InsertOrderItemLocalUseCase
    private val repository: InsertOrderItemLocalRepository = mockk<InsertOrderItemLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = InsertOrderItemLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item return SUCCESS`() = runTest {
        coEvery { repository.insertItem(Order()) } returns flowOf(
            Resource.Success(1)
        )

        val result = useCase.insertItem(Order())
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item return Error`() = runTest {
        coEvery { repository.insertItem(Order()) } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.insertItem(Order())
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}