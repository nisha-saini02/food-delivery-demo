package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.MenuListRepository
import io.mockk.coEvery
import io.mockk.mockk
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
class MenuListUseCaseTest {

    private lateinit var useCase: MenuListUseCase
    private val repository: MenuListRepository = mockk<MenuListRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = MenuListUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch menu return SUCCESS`() = runTest {
        coEvery { repository.getMenuList("") } returns flowOf(
            Resource.Success(SubCategoryDetailsResponse(mutableListOf()))
        )

        val result = useCase.getMenuList("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch menu return Error`() = runTest {
        coEvery { repository.getMenuList("") } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getMenuList("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }
    
}