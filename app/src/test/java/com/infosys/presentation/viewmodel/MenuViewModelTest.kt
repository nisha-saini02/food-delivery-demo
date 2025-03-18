package com.infosys.presentation.viewmodel

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.MenuListUseCase
import com.infosys.domain.usecase.SubCategoriesUseCase
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

//@RunWith(MockitoJUnitRunner::class)
class MenuViewModelTest {
    private lateinit var viewModel: MenuViewModel
    private lateinit var useCase: RemoteUseCase
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
//        MockitoAnnotations.initMocks(this)
        val allCategoriesUseCase = mock(AllCategoriesUseCase::class.java)
//        val allCategoriesUseCase = AllCategoriesUseCase(mock(AllCategoriesRepository::class.java))
        val subCategoriesUseCase = mock(SubCategoriesUseCase::class.java)
        val subCategoryDetailsUseCase = mock(SubCategoryDetailsUseCase::class.java)
        val menuListUseCase = mock(MenuListUseCase::class.java)

        useCase = RemoteUseCase(allCategoriesUseCase, subCategoriesUseCase, subCategoryDetailsUseCase, menuListUseCase)
//        useCase = mock(RemoteUseCase::class.java)

        viewModel = MenuViewModel(useCase)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `fetch categories return SUCCESS`() {
        runTest {
            val expected = CategoryResponse(mutableListOf())

            `when`(
                useCase.allCategoriesUseCase.getAllCategories()
            ).thenReturn(Resource.Success(expected))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            assertEquals(expected.categories, viewModel.categories.value?.categories)
//            viewModel.categories.test {
//                val result = awaitItem()
//                assertEquals(expected.categories, result?.categories)
//            }
        }
    }

    /*@Test
    fun `fetch sub categories return SUCCESS`() {
        runTest {
            coEvery {
                useCase.subCategoriesUseCase.getSubCategories("1")
            } returns flowOf(Resource.Success(
                SubCategoryResponse(mutableListOf())
            ))

            viewModel.getSubCategories("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategories.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

            coVerify {
                useCase.subCategoriesUseCase.getSubCategories("1")
            }
        }
    }

    @Test
    fun `fetch sub categories return null`() {
        runTest {
            coEvery {
                useCase.subCategoriesUseCase.getSubCategories("1")
            } returns flowOf(Resource.Error("Test"))

            viewModel.getSubCategories("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategories.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.subCategoriesUseCase.getSubCategories("1")
            }
        }
    }*/

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

}