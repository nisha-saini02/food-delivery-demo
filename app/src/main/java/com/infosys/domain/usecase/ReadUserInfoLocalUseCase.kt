package com.infosys.domain.usecase

import com.infosys.domain.repository.ReadUserInfoLocalRepository
import javax.inject.Inject

class ReadUserInfoLocalUseCase @Inject constructor(var repository: ReadUserInfoLocalRepository) {
    fun getUserInfo() = repository.readUserInfo()
}