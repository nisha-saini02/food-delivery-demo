package com.infosys.presentation.viewmodel

import com.infosys.data.model.user.User
import com.infosys.domain.usecase.WriteUserInfoLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SignupUserViewModelTest {

    private lateinit var viewModel: SignupUserViewModel
    private val writeUserInfoLocalUseCase = mock(WriteUserInfoLocalUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = SignupUserViewModel(writeUserInfoLocalUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun writeUserInfo_success() = runTest {
        val user = User()
        `when`(writeUserInfoLocalUseCase.writeUserInfo(user))
            .thenReturn(Unit)

        viewModel.writeUserInfo(user)
        dispatcher.scheduler.advanceUntilIdle()
    }

}