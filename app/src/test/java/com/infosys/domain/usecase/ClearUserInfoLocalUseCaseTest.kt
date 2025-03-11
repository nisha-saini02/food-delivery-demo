package com.infosys.domain.usecase

import com.infosys.domain.repository.ClearUserInfoLocalRepository
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
class ClearUserInfoLocalUseCaseTest {

    private lateinit var useCase: ClearUserInfoLocalUseCase
    private val repository: ClearUserInfoLocalRepository = mockk<ClearUserInfoLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = ClearUserInfoLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `clear user info return Unit`() = runTest {
        coEvery { repository.clearUserInfo() } returns Unit

        val result = useCase.clearUserInfo()
        advanceUntilIdle()
        assert(result == Unit)
    }
    
}