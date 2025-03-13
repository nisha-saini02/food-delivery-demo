package com.infosys.domain.usecase

import com.infosys.domain.repository.ClearUserInfoLocalRepository
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
class ClearUserInfoLocalUseCaseTest {

    private lateinit var useCase: ClearUserInfoLocalUseCase
    @Mock private lateinit var repository: ClearUserInfoLocalRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = ClearUserInfoLocalUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `clear user info return Unit`() = runTest {
        `when`(
            repository.clearUserInfo()
        ).thenReturn(Unit)

        val result = useCase.clearUserInfo()
        dispatcher.scheduler.advanceUntilIdle()
        assertTrue(result == Unit)

        verify(repository).clearUserInfo()
    }
    
}