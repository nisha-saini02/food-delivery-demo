package com.infosys.domain.usecase

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCategoriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class AllCategoriesUseCaseTest {
    
    private lateinit var useCase: AllCategoriesUseCase
    private val repository: AllCategoriesRepository = mockk<AllCategoriesRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = AllCategoriesUseCase(repository)
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
        coEvery { repository.getAllCategories() } returns flowOf(Resource.Success(CategoryResponse(
            mutableListOf()
        )))

        val result = useCase.getAllCategories()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all categories return Error`() = runTest {
        coEvery { repository.getAllCategories() } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getAllCategories()
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }
    
}