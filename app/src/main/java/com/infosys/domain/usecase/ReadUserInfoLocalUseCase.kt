package com.infosys.domain.usecase

import com.infosys.domain.repository.UserInfoLocalRepository

class ReadUserInfoLocalUseCase (var repository: UserInfoLocalRepository) {
//    fun getUserInfo() = repository.readUserInfo()
    operator fun invoke() = repository.readUserInfo()
}