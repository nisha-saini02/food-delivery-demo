package com.infosys.domain.repository

import com.infosys.data.model.card.Card

interface CardsInfoRepository {
    suspend fun insertCard(card: Card): Long
    suspend fun deleteCard(card: Card): Int
    suspend fun fetchCards(): List<Card>
}