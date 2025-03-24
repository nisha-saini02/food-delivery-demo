package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.user.User
import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.ReadUserInfoLocalUseCase
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
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UserViewModelTest {

    private lateinit var viewModel: UserViewModel
    private val readUserInfoLocalUseCase = mock(ReadUserInfoLocalUseCase::class.java)
    private val clearUserInfoLocalUseCase = mock(ClearUserInfoLocalUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = UserViewModel(readUserInfoLocalUseCase, clearUserInfoLocalUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun readUserInfo_success() = runTest {
        val user = User()
        `when`(readUserInfoLocalUseCase())
            .thenReturn(flowOf(user))

        viewModel.readUserInfo()
        dispatcher.scheduler.advanceUntilIdle()

        viewModel.userInfo.test {
            assertEquals(user, awaitItem())
        }
    }

    @Test
    fun readUserInfo_exception() = runTest {
        `when`(readUserInfoLocalUseCase())
            .thenThrow(RuntimeException("Tst"))

        viewModel.readUserInfo()
        dispatcher.scheduler.advanceUntilIdle()

        viewModel.userInfo.test {
            assertEquals(null, awaitItem())
        }
    }

    @Test
    fun clearUserInfo_success() = runTest {
        `when`(clearUserInfoLocalUseCase())
            .thenReturn(Unit)

        viewModel.clearUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
    }

}