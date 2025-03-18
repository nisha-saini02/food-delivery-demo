package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
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
class MenusAndMenusAndSubCategoryDetailsRepositoryImplTest {

    private lateinit var repository: MenusAndSubCategoryDetailsRepositoryImpl
    private val dao: FoodService = mockk<FoodService>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = MenusAndSubCategoryDetailsRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch subcategory details return SUCCESS`() = runTest {
        coEvery { dao.getSubCategoryDetails("") } returns Response.success(
            SubCategoryDetailsResponse(mutableListOf())
        )

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch subcategory details return ERROR`() = runTest {
        coEvery { dao.getSubCategoryDetails("") } returns Response.error(400, mockk(relaxed = true))

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch subcategory details throw EXCEPTION`() = runTest {
        coEvery { dao.getSubCategoryDetails("") } throws Exception("Test Error")

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }
    
}