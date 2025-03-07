package com.infosys.domain.usecase

import com.infosys.data.model.user.User
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import javax.inject.Inject

class WriteUserInfoLocalUseCase @Inject constructor(var repository: WriteUserInfoLocalRepository) {
    suspend fun writeUserInfo(userInfo: User) {
        return repository.writeUserInfo(userInfo)
    }
}