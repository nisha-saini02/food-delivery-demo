package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.SubCategoriesRepository
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
class SubCategoriesUseCaseTest {

    private lateinit var useCase: SubCategoriesUseCase
    private val repository: SubCategoriesRepository = mockk<SubCategoriesRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = SubCategoriesUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch sub categories return SUCCESS`() = runTest {
        coEvery { repository.getSubCategories("") } returns flowOf(
            Resource.Success(SubCategoryResponse(mutableListOf()))
        )

        val result = useCase.getSubCategories("")
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch sub categories return Error`() = runTest {
        coEvery { repository.getSubCategories("") } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getSubCategories("")
        advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }
    }

}