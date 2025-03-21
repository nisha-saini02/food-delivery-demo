//package com.infosys.data.repositoryImpl
//
//import com.infosys.data.localDatabase.MyDataStore
//import io.mockk.coEvery
//import io.mockk.mockk
//import junit.framework.TestCase.assertTrue
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class ClearUserInfoLocalRepositoryImplTest {
//
//    private lateinit var repository: ClearUserInfoLocalRepositoryImpl
//    private val dao: MyDataStore = mockk<MyDataStore>(relaxed = true)
//    private val dispatcher = StandardTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun init() {
//        repository = ClearUserInfoLocalRepositoryImpl(dao)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @After
//    fun dispose() {
//        Dispatchers.resetMain()
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `clear user info return Unit`() = runTest {
//        coEvery { dao.clearUserInfo() } returns Unit
//
//        val result = repository.clearUserInfo()
//        advanceUntilIdle()
//        assertTrue(result == Unit)
//    }
//
//}