package com.infosys.presentation.viewmodel

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.usecase.MenuListUseCase
import com.infosys.domain.usecase.SubCategoryDetailsUseCase
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

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val subCategoryDetailsUseCase = mock(SubCategoryDetailsUseCase::class.java)
    private val menuListUseCase = mock(MenuListUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(subCategoryDetailsUseCase, menuListUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getSubcategoriesDetails_success() = runTest {
        val expected = SubCategoryDetailsResponse(mutableListOf(SubCategoryDetails("1")))
        `when`(subCategoryDetailsUseCase.getSubCategoryDetails("1"))
            .thenReturn(Resource.Success(expected))

        viewModel.getSubCategoryDetails("1")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.subcategoriesDetails.value.data)
        assertEquals(expected.meals, viewModel.subcategoriesDetails.value.data?.meals)
    }

    @Test
    fun getSubcategoriesDetails_failure() = runTest {
        val expected = null
        `when`(subCategoryDetailsUseCase.getSubCategoryDetails("1"))
            .thenReturn(Resource.Success(expected))

        viewModel.getSubCategoryDetails("1")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.subcategoriesDetails.value.data)
        assertEquals(expected, viewModel.subcategoriesDetails.value.data?.meals)
    }

    @Test
    fun getSubcategoriesDetails_exception() = runTest {
        val expected = null
        `when`(subCategoryDetailsUseCase.getSubCategoryDetails("1"))
            .thenThrow(RuntimeException::class.java)

        viewModel.getSubCategoryDetails("1")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.subcategoriesDetails.value.data)
    }

    @Test
    fun getMeals_success() = runTest {
        val expected = SubCategoryDetailsResponse(mutableListOf(SubCategoryDetails("")))
        `when`(menuListUseCase.getMenuList(""))
            .thenReturn(Resource.Success(expected))

        viewModel.getMenuList("")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.meals.value.data)
        assertEquals(expected.meals, viewModel.meals.value.data?.meals)
    }

    @Test
    fun getMeals_failure() = runTest {
        val expected = null
        `when`(menuListUseCase.getMenuList(""))
            .thenReturn(Resource.Success(expected))

        viewModel.getMenuList("")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.meals.value.data)
    }

    @Test
    fun getMeals_exception() = runTest {
        val expected = null
        `when`(menuListUseCase.getMenuList(""))
            .thenThrow(RuntimeException::class.java)

        viewModel.getMenuList("")

        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, viewModel.meals.value.data)
    }
}