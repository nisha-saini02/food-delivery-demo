package com.infosys.domain.usecase

import com.infosys.data.model.user.User
import com.infosys.domain.repository.UserInfoLocalRepository

data class WriteUserInfoLocalUseCase (var repository: UserInfoLocalRepository) {
    suspend fun writeUserInfo(userInfo: User) {
        return repository.writeUserInfo(userInfo)
    }
}