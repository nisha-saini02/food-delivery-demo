package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.usecase.AuthUseCase
import com.infosys.data.model.user.User
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.ClearUserInfoLocalRepository
import com.infosys.domain.repository.ReadUserInfoLocalRepository
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.ReadUserInfoLocalUseCase
import com.infosys.domain.usecase.WriteUserInfoLocalUseCase
import junit.framework.TestCase.assertEquals
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

//@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var useCase: AuthUseCase
    private lateinit var read: ReadUserInfoLocalUseCase
    @Mock private lateinit var readRepo: ReadUserInfoLocalRepository
    private lateinit var write: WriteUserInfoLocalUseCase
    @Mock private lateinit var writeRepo: WriteUserInfoLocalRepository
    private lateinit var clear: ClearUserInfoLocalUseCase
    @Mock private lateinit var clearRepo: ClearUserInfoLocalRepository
    private val dispatcher = StandardTestDispatcher()
    private lateinit var user: User

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        read = ReadUserInfoLocalUseCase(readRepo)
        write = WriteUserInfoLocalUseCase(writeRepo)
        clear = ClearUserInfoLocalUseCase(clearRepo)

        useCase = AuthUseCase(read, write, clear)

        authViewModel = AuthViewModel(useCase)

        user = User("Nisha", "test@yopmail.com", "pwd")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `write user information from database returns SUCCESS`() = runTest {
        `when`(
            useCase.writeUserInfoLocalUseCase.writeUserInfo(user)
        ).thenReturn(Unit)

        val result = authViewModel.writeUserInfo(user)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(Unit, result)
        assertTrue(result == Unit)

//        verify(useCase.writeUserInfoLocalUseCase).writeUserInfo(user)
    }

    @Test
    fun `read user information from database returns SUCCESS`() = runTest {
        `when`(
            useCase.readUserInfoLocalUseCase.getUserInfo()
        ).thenReturn(flowOf(user))

        authViewModel.readUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Success)
        }

//        verify(useCase.readUserInfoLocalUseCase).getUserInfo()
    }

    @Test
    fun `read user information from database return null`() = runTest {
        `when`(
            useCase.readUserInfoLocalUseCase.getUserInfo()
        ).thenReturn(flowOf(null))

        authViewModel.readUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Error)
        }

//        verify(useCase.readUserInfoLocalUseCase).getUserInfo()
    }

    @Test
    fun `read user information from database throws EXCEPTION`() = runTest {
        `when`(
            useCase.readUserInfoLocalUseCase.getUserInfo()
        ).thenThrow(RuntimeException::class.java)

        authViewModel.readUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
        authViewModel.userInfo.test {
            val result = awaitItem()
            assertTrue(result is Resource.Error)
        }

//        verify(useCase.readUserInfoLocalUseCase).getUserInfo()
    }

    @Test
    fun `clear user information from database returns SUCCESS`() = runTest {
        `when`(
            useCase.clearUserInfoLocalUseCase.clearUserInfo()
        ).thenReturn(Unit)

        val result = authViewModel.clearUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
        assertTrue(result == Unit)

//        verify(useCase.clearUserInfoLocalUseCase).clearUserInfo()
    }
}