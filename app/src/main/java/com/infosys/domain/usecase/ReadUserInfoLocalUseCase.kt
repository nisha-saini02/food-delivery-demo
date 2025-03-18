package com.infosys.domain.usecase

import com.infosys.domain.repository.UserInfoLocalRepository

data class ReadUserInfoLocalUseCase (var repository: UserInfoLocalRepository) {
    fun getUserInfo() = repository.readUserInfo()
}