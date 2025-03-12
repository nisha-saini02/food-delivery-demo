package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
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
class AllCartItemsLocalRepositoryImplTest {

    private lateinit var repository: AllCartItemsLocalRepositoryImpl
    private val dao: CartDao = mockk<CartDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = AllCartItemsLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all cart items return SUCCESS`() = runTest {
        coEvery { dao.getAll() } returns emptyList()

        val result = repository.fetchAllItems()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all cart items throw EXCEPTION`() = runTest {
        coEvery { dao.getAll() } throws Exception("Test Error")

        val result = repository.fetchAllItems()
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}