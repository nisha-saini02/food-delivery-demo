package com.infosys.presentation.viewmodel

import com.infosys.data.model.card.Card
import com.infosys.domain.usecase.CardsInfoUseCase
import com.infosys.domain.usecase.DeleteCardInfoUseCase
import com.infosys.domain.usecase.InsertCardInfoUseCase
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

class CardInfoViewModelTest {

    private lateinit var viewModel: CardInfoViewModel
    private val insertCardInfoUseCase = mock(InsertCardInfoUseCase::class.java)
    private val deleteCardInfoUseCase = mock(DeleteCardInfoUseCase::class.java)
    private val cardsInfoUseCase = mock(CardsInfoUseCase::class.java)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = CardInfoViewModel(insertCardInfoUseCase, deleteCardInfoUseCase, cardsInfoUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCards_data() = runTest {
        val expected = listOf(Card())
        `when`(cardsInfoUseCase.fetchCards()).thenReturn(expected)

        viewModel.getAllCards()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.card.value)
    }

    @Test
    fun getCards_null() = runTest {
        val expected = null
        `when`(cardsInfoUseCase.fetchCards()).thenReturn(expected)

        viewModel.getAllCards()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(expected, viewModel.card.value)
    }

    @Test
    fun getCards_Exception() = runTest {
        `when`(cardsInfoUseCase.fetchCards()).thenThrow(RuntimeException::class.java)

        viewModel.getAllCards()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.card.value)
    }

    @Test
    fun insertItem_Success() = runTest {
        val expected = Card()
        `when`(insertCardInfoUseCase.insertCard(expected)).thenReturn(1)

        viewModel.insertItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.insertCard.value)
    }

    @Test
    fun insertItem_failure() = runTest {
        val expected = Card()
        `when`(insertCardInfoUseCase.insertCard(expected)).thenReturn(-1)

        viewModel.insertItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(-1, viewModel.insertCard.value)
    }

    @Test
    fun insertItem_Exception() = runTest {
        val expected = Card()
        `when`(insertCardInfoUseCase.insertCard(expected)).thenThrow(RuntimeException::class.java)

        viewModel.insertItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(0, viewModel.insertCard.value)
    }

    @Test
    fun deleteItem_success() = runTest {
        val expected = Card()
        `when`(deleteCardInfoUseCase.deleteCard(expected)).thenReturn(1)

        viewModel.deleteItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.deleteCard.value)
    }

    @Test
    fun deleteItem_failure() = runTest {
        val expected = Card()
        `when`(deleteCardInfoUseCase.deleteCard(expected)).thenReturn(-1)

        viewModel.deleteItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(-1, viewModel.deleteCard.value)
    }

    @Test
    fun deleteItem_exception() = runTest {
        val expected = Card()
        `when`(deleteCardInfoUseCase.deleteCard(expected)).thenThrow(RuntimeException::class.java)

        viewModel.deleteItem(expected)
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(0, viewModel.deleteCard.value)
    }
}