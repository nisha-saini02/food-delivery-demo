package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
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

class MenusAndSubCategoryDetailsRepositoryImplTest {
    
    private lateinit var repository: MenusAndSubCategoryDetailsRepositoryImpl
    private val dao: FoodService = mock(FoodService::class.java)
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
    fun getSubCategoryDetails_success() = runTest {
        val data = mutableListOf<SubCategoryDetails>()
        `when`(dao.getSubCategoryDetails(""))
            .thenReturn(Response.success(SubCategoryDetailsResponse(data)))

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()

        assertTrue(result is Resource.Success)
        assertEquals(data, result.data?.meals)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSubCategoryDetails_error() = runTest {
        `when`(dao.getSubCategoryDetails(""))
            .thenReturn(Response.error(400, mockk(relaxed = true)))

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSubCategoryDetails_exception() = runTest {
        `when`(dao.getSubCategoryDetails(""))
            .thenThrow(RuntimeException("Test"))

        val result = repository.getSubCategoryDetails("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMenuList_success() = runTest {
        val data = mutableListOf<SubCategoryDetails>()
        `when`(dao.getMenuList(""))
            .thenReturn(Response.success(SubCategoryDetailsResponse(data)))

        val result = repository.getMenuList("")
        advanceUntilIdle()

        assertTrue(result is Resource.Success)
        assertEquals(data, result.data?.meals)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMenuList_error() = runTest {
        `when`(dao.getMenuList(""))
            .thenReturn(Response.error(400, mockk(relaxed = true)))

        val result = repository.getMenuList("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMenuList_exception() = runTest {
        `when`(dao.getMenuList(""))
            .thenThrow(RuntimeException("Test"))

        val result = repository.getMenuList("")
        advanceUntilIdle()

        assertEquals(null, result.data)
    }

}