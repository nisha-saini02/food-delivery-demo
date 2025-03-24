package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CardDao
import com.infosys.data.model.card.Card
import junit.framework.TestCase.assertEquals
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CardsInfoRepositoryImplTest {

    private lateinit var repository: CardsInfoRepositoryImpl
    private val dao = mock(CardDao::class.java)
    private val card = Card()
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        repository = CardsInfoRepositoryImpl(dao)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertCard_success() = runTest {
        `when`(dao.insertCard(card))
            .thenReturn(1)

        val result = repository.insertCard(card)
        advanceUntilIdle()

        assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertCard_failure() = runTest {
        `when`(dao.insertCard(card))
            .thenReturn(-1)

        val result = repository.insertCard(card)
        advanceUntilIdle()

        assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteCard_success() = runTest {
        `when`(dao.deleteCard(card))
            .thenReturn(1)

        val result = repository.deleteCard(card)
        advanceUntilIdle()

        assertEquals(1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteCard_failure() = runTest {
        `when`(dao.deleteCard(card))
            .thenReturn(-1)

        val result = repository.deleteCard(card)
        advanceUntilIdle()

        assertEquals(-1, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchCards_success() = runTest {
        val list = mutableListOf<Card>()
        `when`(dao.getAll())
            .thenReturn(list)

        val result = repository.fetchCards()
        advanceUntilIdle()

        assertEquals(list, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchCards_failure() = runTest {
        val list = mutableListOf<Card>()
        `when`(dao.getAll())
            .thenReturn(list)

        val result = repository.fetchCards()
        advanceUntilIdle()

        assertEquals(list, result)
    }
}