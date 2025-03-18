package com.infosys.domain.repository

import com.infosys.data.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserInfoLocalRepository {
    fun readUserInfo(): Flow<User?>
    suspend fun clearUserInfo()
    suspend fun writeUserInfo(userInfo: User)
}