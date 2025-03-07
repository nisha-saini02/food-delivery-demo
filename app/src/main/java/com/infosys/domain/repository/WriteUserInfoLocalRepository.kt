package com.infosys.domain.repository

import com.infosys.data.model.user.User

interface WriteUserInfoLocalRepository {
    suspend fun writeUserInfo(userInfo: User)
}