package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.model.user.User
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UserInfoLocalRepositoryImplTest {

    private lateinit var repository: UserInfoLocalRepositoryImpl
    private val dao = mock(MyDataStore::class.java)
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
    fun readUserInfo_SUCCESS() = runTest {
        val user = User()
        `when`(dao.readUserInfo)
            .thenReturn(flowOf(user))

        val result = repository.readUserInfo()
        advanceUntilIdle()

        result.collect {
            assertTrue(it == user)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeUserInfo_Unit() = runTest {
        `when`(dao.writeUserInfo(User()))
            .thenReturn(Unit)

        val result = repository.writeUserInfo(User())
        advanceUntilIdle()

        assertTrue(result == Unit)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clearUserInfo_Unit() = runTest {
        `when`(dao.clearUserInfo())
            .thenReturn(Unit)

        val result = repository.clearUserInfo()
        advanceUntilIdle()
        assertTrue(result == Unit)
    }

}