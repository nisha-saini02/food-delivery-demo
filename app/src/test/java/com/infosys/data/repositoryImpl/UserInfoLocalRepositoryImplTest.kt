package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.model.user.User
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
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
class UserInfoLocalRepositoryImplTest {

    private lateinit var repository: UserInfoLocalRepositoryImpl
    private val dao: MyDataStore = mockk<MyDataStore>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = UserInfoLocalRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `read user info return SUCCESS`() = runTest {
        val user = User()
        coEvery { dao.readUserInfo } returns flowOf(user)

        val result = repository.readUserInfo()
        advanceUntilIdle()
        result.collect {
            assertTrue(it == user)
        }
    }

}