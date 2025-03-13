package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.model.usecase.RemoteUseCase
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.coVerify
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
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    val rule = object : TestWatcher() {}

    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var useCase: RemoteUseCase
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(useCase)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch categories return SUCCESS`() {
        runTest {
            `when`(
                useCase.allCategoriesUseCase.getAllCategories()
            ).thenReturn(flowOf(Resource.Success(
                CategoryResponse(mutableListOf())
            )))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.categories.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

            verify(useCase.allCategoriesUseCase).getAllCategories()
        }
    }

    @Test
    fun `fetch categories return null`() {
        runTest {
            coEvery {
                useCase.allCategoriesUseCase.getAllCategories()
            } returns flowOf(Resource.Error("Test"))

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.categories.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.allCategoriesUseCase.getAllCategories()
            }
        }
    }

    @Test
    fun `fetch categories throw EXCEPTION`() {
        runTest {
            coEvery {
                useCase.allCategoriesUseCase.getAllCategories()
            } throws Exception("Test Exception")

            viewModel.getAllCategories()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.categories.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.allCategoriesUseCase.getAllCategories()
            }
        }
    }

    @Test
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
    }

    @Test
    fun `fetch sub categories throw EXCEPTION`() {
        runTest {
            coEvery {
                useCase.subCategoriesUseCase.getSubCategories("1")
            } throws Exception("Test Exception")

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
    }

    @Test
    fun `fetch sub category details return SUCCESS`() {
        runTest {
            coEvery {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            } returns flowOf(Resource.Success(
                SubCategoryDetailsResponse(mutableListOf())
            ))

            viewModel.getSubCategoryDetails("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategoriesDetails.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

            coVerify {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            }
        }
    }

    @Test
    fun `fetch sub category details return null`() {
        runTest {
            coEvery {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            } returns flowOf(Resource.Error("Test"))

            viewModel.getSubCategoryDetails("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategoriesDetails.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            }
        }
    }

    @Test
    fun `fetch sub category details throw EXCEPTION`() {
        runTest {
            coEvery {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            } throws Exception("Test Exception")

            viewModel.getSubCategoryDetails("1")
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.subcategoriesDetails.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails("1")
            }
        }
    }

    @Test
    fun `fetch menu return SUCCESS`() {
        runTest {
            coEvery {
                useCase.menuListUseCase.getMenuList("")
            } returns flowOf(Resource.Success(
                SubCategoryDetailsResponse(mutableListOf())
            ))

            viewModel.getMenuList()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.meals.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

            coVerify {
                useCase.menuListUseCase.getMenuList("")
            }
        }
    }

    @Test
    fun `fetch menu return null`() {
        runTest {
            coEvery {
                useCase.menuListUseCase.getMenuList("")
            } returns flowOf(Resource.Error("Test"))

            viewModel.getMenuList()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.meals.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.menuListUseCase.getMenuList("")
            }
        }
    }

    @Test
    fun `fetch menu throw EXCEPTION`() {
        runTest {
            coEvery {
                useCase.menuListUseCase.getMenuList("")
            } throws Exception("Test Exception")

            viewModel.getMenuList()
            dispatcher.scheduler.advanceUntilIdle()
            viewModel.meals.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

            coVerify {
                useCase.menuListUseCase.getMenuList("")
            }
        }
    }

}