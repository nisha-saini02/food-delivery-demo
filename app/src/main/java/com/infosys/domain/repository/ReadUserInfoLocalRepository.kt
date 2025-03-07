package com.infosys.domain.repository

import com.infosys.data.model.user.User
import kotlinx.coroutines.flow.Flow

interface ReadUserInfoLocalRepository {
    fun readUserInfo(): Flow<User?>
}