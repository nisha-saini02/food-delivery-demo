package com.infosys.domain.repository

import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface CountCartItemsLocalRepository {
    suspend fun getCartListCount(): Flow<Resource<Int?>>
}