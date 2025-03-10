package com.infosys.domain.repository

import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface GrandTotalCartItemsLocalRepository {
    suspend fun getCartListGrandTotalCount(): Flow<Resource<Float?>>
}