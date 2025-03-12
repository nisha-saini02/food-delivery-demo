package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.data.model.user.User
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.UpdateCartItemLocalRepository
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
class UpdateCartItemLocalUseCaseTest {

    private lateinit var useCase: UpdateCartItemLocalUseCase
    private val repository: UpdateCartItemLocalRepository = mockk<UpdateCartItemLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = UpdateCartItemLocalUseCase(repository)
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
        val user = User()
        coEvery { repository.updateItem(Cart()) } returns flowOf(Resource.Success(1))

        val result = useCase.updateItem(Cart())
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item return ERROR`() = runTest {
        coEvery { repository.updateItem(Cart()) } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.updateItem(Cart())
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

}