//package com.infosys.domain.usecase
//
//import com.infosys.data.model.user.User
//import com.infosys.domain.repository.UserInfoLocalRepository
//import junit.framework.TestCase.assertTrue
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.verify
//import org.mockito.Mockito.`when`
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class ReadUserInfoLocalUseCaseTest {
//
//    private lateinit var useCase: ReadUserInfoLocalUseCase
//    @Mock
//    private lateinit var repository: UserInfoLocalRepository
//    private val dispatcher = StandardTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun init() {
//        MockitoAnnotations.initMocks(this)
//        useCase = ReadUserInfoLocalUseCase(repository)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @After
//    fun dispose() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `read user info return SUCCESS`() = runTest {
//        val user = User()
//        `when`(
//            repository.readUserInfo()
//        ).thenReturn(flowOf(user))
//
//        val result = useCase.getUserInfo()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it == user)
//        }
//
//        verify(repository).readUserInfo()
//    }
//
//    @Test
//    fun `read user info return null`() = runTest {
//        `when`(
//            repository.readUserInfo()
//        ).thenReturn(flowOf(null))
//
//        val result = useCase.getUserInfo()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it == null)
//        }
//
//        verify(repository).readUserInfo()
//    }
//
//}