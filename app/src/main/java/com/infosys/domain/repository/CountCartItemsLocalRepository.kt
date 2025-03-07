package com.infosys.domain.repository

import kotlinx.coroutines.flow.Flow

interface CountCartItemsLocalRepository {
    suspend fun getCartListCount(): Flow<Int?>
}