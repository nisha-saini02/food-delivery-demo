package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.Category
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class AllCategoriesAndSubCategoriesRepositoryImplTest {

    private lateinit var repository: AllCategoriesAndSubCategoriesRepositoryImpl
    private val dao: FoodService = mock(FoodService::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = AllCategoriesAndSubCategoriesRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchCategories_success() = runTest {
        val data = mutableListOf<Category>()
        `when`(dao.allCategory())
            .thenReturn(Response.success(CategoryResponse(data)))

        val result = repository.getAllCategories()
        advanceUntilIdle()

        assertTrue(result is Resource.Success)
        assertEquals(data, result.data?.categories)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchCategories_error() = runTest {
        `when`(dao.allCategory())
            .thenReturn(Response.error(400, mockk(relaxed = true)))

        val result = repository.getAllCategories()
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchCategories_exception() = runTest {
        `when`(dao.allCategory())
            .thenThrow(RuntimeException("Test"))

        val result = repository.getAllCategories()
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchSubCategories_success() = runTest {
        val data = mutableListOf<SubCategory>()
        `when`(dao.getSubCategories(""))
            .thenReturn(Response.success(SubCategoryResponse(data)))

        val result = repository.getSubCategories("")
        advanceUntilIdle()

        assertTrue(result is Resource.Success)
        assertEquals(data, result.data?.meals)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchSubCategories_error() = runTest {
        `when`(dao.getSubCategories(""))
            .thenReturn(Response.error(400, mockk(relaxed = true)))

        val result = repository.getSubCategories("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchSubCategories_exception() = runTest {
        `when`(dao.getSubCategories(""))
            .thenThrow(RuntimeException("Test"))

        val result = repository.getSubCategories("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

}