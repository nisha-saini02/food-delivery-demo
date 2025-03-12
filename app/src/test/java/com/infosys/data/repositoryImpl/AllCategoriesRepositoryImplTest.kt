package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
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
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AllCategoriesRepositoryImplTest {

    private lateinit var repository: AllCategoriesRepositoryImpl
    private val dao: FoodService = mockk<FoodService>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = AllCategoriesRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all categories return SUCCESS`() = runTest {
        coEvery { dao.allCategory() } returns Response.success(CategoryResponse(mutableListOf()))

        val result = repository.getAllCategories()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all categories return ERROR`() = runTest {
        coEvery { dao.allCategory() } returns Response.error(400, mockk(relaxed = true))

        val result = repository.getAllCategories()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all categories throw EXCEPTION`() = runTest {
        coEvery { dao.allCategory() } throws Exception("Test Error")

        val result = repository.getAllCategories()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

}