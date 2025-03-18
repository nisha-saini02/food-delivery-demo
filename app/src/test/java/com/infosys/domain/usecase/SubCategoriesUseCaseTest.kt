package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SubCategoriesUseCaseTest {

    private lateinit var useCase: SubCategoriesUseCase
    @Mock
    private lateinit var repository: SubCategoriesRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = SubCategoriesUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch sub categories return SUCCESS`() = runTest {
        `when`(
            repository.getSubCategories("")
        ).thenReturn(flowOf(Resource.Success(SubCategoryResponse(mutableListOf())))
        )

        val result = useCase.getSubCategories("")
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }

        verify(repository).getSubCategories("")
    }

    @Test
    fun `fetch sub categories return Error`() = runTest {
        `when`(
            repository.getSubCategories("")
        ).thenReturn(flowOf(Resource.Error("Test Error")))

        val result = useCase.getSubCategories("")
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }

        verify(repository).getSubCategories("")
    }

}