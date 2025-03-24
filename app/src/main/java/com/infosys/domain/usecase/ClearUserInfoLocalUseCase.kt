package com.infosys.domain.usecase

import com.infosys.domain.repository.UserInfoLocalRepository

data class ClearUserInfoLocalUseCase(var repo: UserInfoLocalRepository) {
    suspend operator fun invoke() { repo.clearUserInfo() }
}