package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CardDao
import com.infosys.data.model.card.Card
import com.infosys.domain.repository.CardsInfoRepository

class CardsInfoRepositoryImpl(private val cardDao: CardDao): CardsInfoRepository {
    override suspend fun insertCard(card: Card): Long {
        return cardDao.insertCard(card)
    }

    override suspend fun deleteCard(card: Card): Int {
        return cardDao.deleteCard(card)
    }

    override suspend fun fetchCards(): List<Card> {
        return cardDao.getAll()
    }
}