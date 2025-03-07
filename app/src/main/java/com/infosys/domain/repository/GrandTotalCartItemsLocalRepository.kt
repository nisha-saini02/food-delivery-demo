package com.infosys.domain.repository

import kotlinx.coroutines.flow.Flow

interface GrandTotalCartItemsLocalRepository {
    suspend fun getCartListGrandTotalCount(): Flow<Float?>
}