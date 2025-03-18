package com.infosys.domain.usecase

import com.infosys.domain.repository.UserInfoLocalRepository

data class ClearUserInfoLocalUseCase(var repo: UserInfoLocalRepository) {
    suspend fun clearUserInfo() {
        repo.clearUserInfo()
    }
}