package com.infosys.domain.usecase

import com.infosys.domain.repository.ClearUserInfoLocalRepository
import javax.inject.Inject

class ClearUserInfoLocalUseCase @Inject constructor(var repo: ClearUserInfoLocalRepository) {
    suspend fun clearUserInfo() {
        repo.clearUserInfo()
    }
}