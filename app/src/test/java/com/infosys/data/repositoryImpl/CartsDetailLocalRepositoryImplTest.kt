//package com.infosys.data.repositoryImpl
//
//import com.infosys.data.localDatabase.dao.CartDao
//import com.infosys.data.remote.Resource
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
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
//class CartsDetailLocalRepositoryImplTest {
//
//    private lateinit var repository: CartsDetailLocalRepositoryImpl
//    @Mock
//    private lateinit var dao: CartDao
//    private val dispatcher = StandardTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun init() {
//        MockitoAnnotations.initMocks(this)
//        repository = CartsDetailLocalRepositoryImpl(dao)
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
//    fun `fetch all cart items return SUCCESS`() = runTest {
//        `when`(
//            dao.getAll()
//        ).thenReturn(emptyList())
//
//        val result = repository.fetchAllItems()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assert(it is Resource.Success)
//        }
//
//        verify(dao).getAll()
//    }
//
//    @Test
//    fun `fetch all cart items throw EXCEPTION`() = runTest {
//        `when`(
//            dao.getAll()
//        ).thenThrow(Exception("Test Error"))
//
//        val result = repository.fetchAllItems()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assert(it is Resource.Error)
//        }
//
//        verify(dao).getAll()
//    }
//
//}