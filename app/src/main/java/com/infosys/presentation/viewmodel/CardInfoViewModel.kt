package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.card.Card
import com.infosys.domain.usecase.CardsInfoUseCase
import com.infosys.domain.usecase.DeleteCardInfoUseCase
import com.infosys.domain.usecase.InsertCardInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(
    private var insertCardInfoUseCase: InsertCardInfoUseCase,
    private var deleteCardInfoUseCase: DeleteCardInfoUseCase,
    private var cardsInfoUseCase: CardsInfoUseCase,
): ViewModel() {

    private var _card = MutableStateFlow<List<Card>?>(emptyList())
    val card: StateFlow<List<Card>?> = _card

    private var _insertCard = MutableStateFlow<Long>(0)
    val insertCard: StateFlow<Long> = _insertCard

    private var _deleteCard = MutableStateFlow<Int>(0)
    val deleteCard: StateFlow<Int> = _deleteCard

    fun getAllCards() {
        viewModelScope.launch {
            try {
                _card.value = cardsInfoUseCase.fetchCards()
            } catch (e: Exception) {
                _card.value = null
            }
        }
    }

    fun insertItem(card: Card) {
        viewModelScope.launch {
            try {
                _insertCard.value = insertCardInfoUseCase.insertCard(card)
            } catch (e: Exception) {
                _insertCard.value = 0
            }
        }
    }

    fun deleteItem(card: Card) {
        viewModelScope.launch {
            try {
                _deleteCard.value = deleteCardInfoUseCase.deleteCard(card)
            } catch (e: Exception) {
                _deleteCard.value = 0
            }
        }
    }
}