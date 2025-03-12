package com.infosys.domain.usecase

import com.infosys.data.model.user.User
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
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
class WriteUserInfoLocalUseCaseTest {

    private lateinit var useCase: WriteUserInfoLocalUseCase
    private val repository: WriteUserInfoLocalRepository = mockk<WriteUserInfoLocalRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = WriteUserInfoLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `write user info return Unit`() = runTest {
        coEvery { repository.writeUserInfo(User()) } returns Unit

        val result = useCase.writeUserInfo(User())
        advanceUntilIdle()
        assertTrue(result == Unit)
    }
}