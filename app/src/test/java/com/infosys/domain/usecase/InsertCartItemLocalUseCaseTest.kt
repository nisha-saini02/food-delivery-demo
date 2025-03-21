//package com.infosys.domain.usecase
//
//import com.infosys.data.model.cart.Cart
//import com.infosys.data.remote.Resource
//import com.infosys.domain.repository.MenuCartLocalRepository
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
//class InsertCartItemLocalUseCaseTest {
//
//    private lateinit var useCase: InsertCartItemLocalUseCase
//    @Mock
//    private lateinit var repository: MenuCartLocalRepository
//    private val dispatcher = StandardTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun init() {
//        MockitoAnnotations.initMocks(this)
//        useCase = InsertCartItemLocalUseCase(repository)
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
//    fun `insert cart item return SUCCESS`() = runTest {
//        `when`(
//            repository.insertItem(Cart())
//        ).thenReturn(flowOf(
//            Resource.Success(1)))
//
//        val result = useCase.insertItem(Cart())
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Success)
//        }
//
//        verify(repository).insertItem(Cart())
//    }
//
//    @Test
//    fun `insert cart item return Error`() = runTest {
//        `when`(
//            repository.insertItem(Cart())
//        ).thenReturn(flowOf(
//            Resource.Error("Test Error")))
//
//        val result = useCase.insertItem(Cart())
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Error)
//        }
//
//        verify(repository).insertItem(Cart())
//    }
//
//}