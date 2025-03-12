package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
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
class InsertOrderItemLocalRepositoryImplTest {

    private lateinit var repository: InsertOrderItemLocalRepositoryImpl
    private val dao = mockk<OrderDao>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = InsertOrderItemLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item return SUCCESS`() = runTest {
        coEvery { dao.insertItem(Order()) } returns 1

        val result = repository.insertItem(Order())
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item throw EXCEPTION`() = runTest {
        coEvery { dao.insertItem(Order()) } throws Exception("Test Error")

        val result = repository.insertItem(Order())
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}