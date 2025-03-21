//package com.infosys.domain.usecase
//
//import com.infosys.data.model.cart.Cart
//import com.infosys.data.remote.Resource
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
//class UpdateCartItemLocalUseCaseTest {
//
//    private lateinit var useCase: UpdateCartItemLocalUseCase
//    @Mock
//    private lateinit var repository: UpdateCartItemLocalRepository
//    private val dispatcher = StandardTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun init() {
//        MockitoAnnotations.initMocks(this)
//        useCase = UpdateCartItemLocalUseCase(repository)
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
//    fun `update cart item return SUCCESS`() = runTest {
//        `when`(
//            repository.updateItem(Cart())
//        ).thenReturn(flowOf(Resource.Success(1)))
//
//        val result = useCase.updateItem(Cart())
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Success)
//        }
//
//        verify(repository).updateItem(Cart())
//    }
//
//    @Test
//    fun `update cart item return ERROR`() = runTest {
//        `when`(
//            repository.updateItem(Cart())
//        ).thenReturn(flowOf(Resource.Error("Test Error")))
//
//        val result = useCase.updateItem(Cart())
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Error)
//        }
//
//        verify(repository).updateItem(Cart())
//    }
//
//}