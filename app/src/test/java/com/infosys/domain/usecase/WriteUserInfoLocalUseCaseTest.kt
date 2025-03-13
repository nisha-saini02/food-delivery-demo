package com.infosys.domain.usecase

import com.infosys.data.model.user.User
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class WriteUserInfoLocalUseCaseTest {

    private lateinit var useCase: WriteUserInfoLocalUseCase
    @Mock
    private lateinit var repository: WriteUserInfoLocalRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = WriteUserInfoLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `write user info return Unit`() = runTest {
        `when`(
            repository.writeUserInfo(User())
        ).thenReturn(Unit)

        val result = useCase.writeUserInfo(User())
        dispatcher.scheduler.advanceUntilIdle()
        assertTrue(result == Unit)

        verify(repository).writeUserInfo(User())
    }
}