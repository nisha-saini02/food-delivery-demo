package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.usecase.AuthUseCase
import com.infosys.data.model.user.User
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private var useCase: AuthUseCase = mockk<AuthUseCase>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        Dispatchers.setMain(dispatcher)
        authViewModel = AuthViewModel(useCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `write user information from database returns SUCCESS`() = runTest {
        coEvery { useCase.writeUserInfoLocalUseCase.writeUserInfo(User()) } returns Unit

        val result = authViewModel.writeUserInfo(User())
        advanceUntilIdle()
        assertTrue(result == Unit)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user information from database returns SUCCESS`() = runTest {
        coEvery { useCase.readUserInfoLocalUseCase.getUserInfo() } returns flowOf(User())

        authViewModel.readUserInfo()
        advanceUntilIdle()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user information from database return null`() = runTest {
        coEvery { useCase.readUserInfoLocalUseCase.getUserInfo() } returns flowOf(null)

        authViewModel.readUserInfo()
        runCurrent()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user information from database throws EXCEPTION`() = runTest {
        coEvery { useCase.readUserInfoLocalUseCase.getUserInfo() } throws Exception("Test Exception")

        authViewModel.readUserInfo()
        advanceUntilIdle()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `clear user information from database returns SUCCESS`() = runTest {
        coEvery { useCase.clearUserInfoLocalUseCase.clearUserInfo() } returns Unit

        val result = authViewModel.clearUserInfo()
        advanceUntilIdle()
        assertTrue(result == Unit)
    }
}