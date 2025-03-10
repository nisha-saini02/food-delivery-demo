package com.infosys.domain.repository

import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface DeleteAllCartsLocalRepository {
    suspend fun deleteAllCarts(): Flow<Resource<Int>>
}