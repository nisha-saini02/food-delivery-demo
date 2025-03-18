package com.infosys.domain.usecase

import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AllCategoriesUseCaseTest {
    
    private lateinit var useCase: AllCategoriesUseCase
    @Mock private lateinit var repository: AllCategoriesAndSubCategoriesRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = AllCategoriesUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch all categories return SUCCESS`() = runTest {
//        `when`(
//            repository.getAllCategories()
//        ).thenReturn(flowOf(Resource.Success(CategoryResponse(mutableListOf()))))
//
//        val result = useCase.getAllCategories()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Success)
//        }
//
//        verify(repository).getAllCategories()
    }

    @Test
    fun `fetch all categories return Error`() = runTest {
//        `when`(
//            repository.getAllCategories()
//        ).thenReturn(flowOf(Resource.Error("Test Error")))
//
//        val result = useCase.getAllCategories()
//        dispatcher.scheduler.advanceUntilIdle()
//        result.collect {
//            assertTrue(it is Resource.Error)
//        }
//
//        verify(repository).getAllCategories()
    }
    
}