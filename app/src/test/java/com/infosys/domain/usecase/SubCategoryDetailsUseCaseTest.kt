package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.SubCategoryDetailsRepository
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
class SubCategoryDetailsUseCaseTest {

    private lateinit var useCase: SubCategoryDetailsUseCase
    @Mock
    private lateinit var repository: SubCategoryDetailsRepository
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        useCase = SubCategoryDetailsUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch sub category details return SUCCESS`() = runTest {
        `when`(
            repository.getSubCategoryDetails("1")
        ).thenReturn(flowOf(Resource.Success(SubCategoryDetailsResponse(mutableListOf()))))

        val result = useCase.getSubCategoryDetails("1")
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Success)
        }

        verify(repository).getSubCategoryDetails("1")
    }

    @Test
    fun `fetch sub category details return Error`() = runTest {
        `when`(
            repository.getSubCategoryDetails("1")
        ).thenReturn(flowOf(Resource.Error("Test Error")))

        val result = useCase.getSubCategoryDetails("1")
        dispatcher.scheduler.advanceUntilIdle()
        result.collect {
            assertTrue(it is Resource.Error)
        }

        verify(repository).getSubCategoryDetails("1")
    }

}