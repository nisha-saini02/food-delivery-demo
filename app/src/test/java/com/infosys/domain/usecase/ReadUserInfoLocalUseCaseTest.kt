package com.infosys.domain.usecase

import com.infosys.data.model.user.User
import com.infosys.domain.repository.ReadUserInfoLocalRepository
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
class ReadUserInfoLocalUseCaseTest {

    private lateinit var useCase: ReadUserInfoLocalUseCase
    private val repository: ReadUserInfoLocalRepository = mockk<ReadUserInfoLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = ReadUserInfoLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user info return SUCCESS`() = runTest {
        val user = User()
        coEvery { repository.readUserInfo() } returns flowOf(user)

        val result = useCase.getUserInfo()
        advanceUntilIdle()
        result.collect {
            assert(it == user)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user info return null`() = runTest {
        coEvery { repository.readUserInfo() } returns flowOf(null)

        val result = useCase.getUserInfo()
        advanceUntilIdle()
        result.collect {
            assert(it == null)
        }
    }
    
}