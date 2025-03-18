package com.infosys.domain.usecase

import com.infosys.data.model.card.Card
import com.infosys.domain.repository.CardsInfoRepository

class InsertCardInfoUseCase(private val cardsInfoRepository: CardsInfoRepository) {
    suspend fun insertCard(card: Card) = cardsInfoRepository.insertCard(card)
}