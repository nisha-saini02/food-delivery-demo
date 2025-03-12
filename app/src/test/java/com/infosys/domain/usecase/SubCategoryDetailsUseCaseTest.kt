package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.SubCategoryDetailsRepository
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
class SubCategoryDetailsUseCaseTest {

    private lateinit var useCase: SubCategoryDetailsUseCase
    private val repository: SubCategoryDetailsRepository = mockk<SubCategoryDetailsRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        useCase = SubCategoryDetailsUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch sub category details return SUCCESS`() = runTest {
        coEvery { repository.getSubCategoryDetails("1") } returns flowOf(
            Resource.Success(SubCategoryDetailsResponse(mutableListOf()))
        )

        val result = useCase.getSubCategoryDetails("1")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch sub category details return Error`() = runTest {
        coEvery { repository.getSubCategoryDetails("1") } returns flowOf(Resource.Error("Test Error"))

        val result = useCase.getSubCategoryDetails("1")
        advanceUntilIdle()
        result.collect {
            assert(it is Resource.Error)
        }
    }

}