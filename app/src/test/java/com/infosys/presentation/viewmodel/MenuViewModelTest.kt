package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.SubCategoriesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class MenuViewModelTest {
    private lateinit var viewModel: MenuViewModel
    private val allCategoriesUseCase = mock(AllCategoriesUseCase::class.java)
    private val subCategoriesUseCase = mock(SubCategoriesUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        viewModel = MenuViewModel(allCategoriesUseCase, subCategoriesUseCase)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun fetchCategories_return_SUCCESS() {
        runTest {
            val expected = CategoryResponse(mutableListOf())

            `when`(
                allCategoriesUseCase.getAllCategories()
            ).thenReturn(Resource.Success(expected))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()

            assertEquals(expected.categories, viewModel.categories.value?.categories)
            viewModel.categories.test {
                val result = awaitItem()
                assertEquals(expected.categories, result?.categories)
            }
        }
    }

    @Test
    fun fetchCategories_return_NULL() {
        runTest {
            `when`(
                allCategoriesUseCase.getAllCategories()
            ).thenReturn(Resource.Error("Test"))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.categories.test {
                val result = awaitItem()
                assertEquals(null, result?.categories)
            }
        }
    }

    @Test
    fun fetchCategories_return_EXCEPTION() {
        runTest {
            val expected = "Test"
            `when`(
                allCategoriesUseCase.getAllCategories()
            ).thenThrow(RuntimeException(expected))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.categories.test {
                val result = awaitItem()
                assertEquals("Test", expected)
                assertEquals(null, result?.categories)
            }
        }
    }

    @Test
    fun fetchSubCategories_return_SUCCESS() {
        runTest {
            val expected = SubCategoryResponse(mutableListOf())
            `when`(
                subCategoriesUseCase.getSubCategories("1")
            ).thenReturn(Resource.Success(expected))

            viewModel.getSubCategories("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategories.test {
                val result = awaitItem()
                assertEquals(expected.meals, result?.meals)
            }
        }
    }

    @Test
    fun fetchSubCategories_return_NULL() {
        runTest {
            `when`(
                subCategoriesUseCase.getSubCategories("1")
            ).thenReturn(Resource.Error("Test"))

            viewModel.getSubCategories("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategories.test {
                val result = awaitItem()
                assertEquals(null, result?.meals)
            }
        }
    }

    @Test
    fun fetchSubCategories_return_EXCEPTION() {
        runTest {
            val expected = "Test"
            `when`(
                subCategoriesUseCase.getSubCategories("1")
            ).thenThrow(RuntimeException(expected))

            viewModel.getSubCategories("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategories.test {
                val result = awaitItem()
                assertEquals("Test", expected)
                assertEquals(null, result?.meals)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

}