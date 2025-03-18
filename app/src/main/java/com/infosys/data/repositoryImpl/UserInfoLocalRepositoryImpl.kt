package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.model.user.User
import com.infosys.domain.repository.UserInfoLocalRepository
import kotlinx.coroutines.flow.Flow

class UserInfoLocalRepositoryImpl(private var store: MyDataStore): UserInfoLocalRepository {
    override fun readUserInfo(): Flow<User?> {
        return store.readUserInfo
    }

    override suspend fun clearUserInfo() {
        store.clearUserInfo()
    }

    override suspend fun writeUserInfo(userInfo: User) {
        store.writeUserInfo(userInfo)
    }
}