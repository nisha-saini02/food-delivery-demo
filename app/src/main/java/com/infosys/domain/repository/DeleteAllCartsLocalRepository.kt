package com.infosys.domain.repository

import kotlinx.coroutines.flow.Flow

interface DeleteAllCartsLocalRepository {
    suspend fun deleteAllCarts(): Flow<Int>
}