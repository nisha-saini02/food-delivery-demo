package com.infosys.domain.usecase

import com.infosys.domain.repository.CardsInfoRepository

class CardsInfoUseCase(private val cardsInfoRepository: CardsInfoRepository) {
    suspend fun fetchCards() = cardsInfoRepository.fetchCards()
}