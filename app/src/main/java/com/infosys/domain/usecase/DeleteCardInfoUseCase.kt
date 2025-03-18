package com.infosys.domain.usecase

import com.infosys.data.model.card.Card
import com.infosys.domain.repository.CardsInfoRepository

class DeleteCardInfoUseCase(private val cardsInfoRepository: CardsInfoRepository) {
    suspend fun deleteCard(card: Card) = cardsInfoRepository.deleteCard(card)
}