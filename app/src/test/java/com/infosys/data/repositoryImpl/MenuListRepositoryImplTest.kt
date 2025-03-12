package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
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
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MenuListRepositoryImplTest {

    private lateinit var repository: MenuListRepositoryImpl
    private val dao: FoodService = mockk<FoodService>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = MenuListRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all menu items return SUCCESS`() = runTest {
        coEvery { dao.getMenuList("") } returns Response.success(SubCategoryDetailsResponse(mutableListOf()))

        val result = repository.getMenuList("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all menu items return ERROR`() = runTest {
        coEvery { dao.getMenuList("") } returns Response.error(400, mockk(relaxed = true))

        val result = repository.getMenuList("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch all menu items throw EXCEPTION`() = runTest {
        coEvery { dao.getMenuList("") } throws Exception("Test Error")

        val result = repository.getMenuList("")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }

}