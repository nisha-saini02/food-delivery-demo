package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.model.user.User
import com.infosys.domain.repository.ReadUserInfoLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadUserInfoLocalRepositoryImpl @Inject constructor(var store: MyDataStore): ReadUserInfoLocalRepository {
    override fun readUserInfo(): Flow<User?> {
        return store.readUserInfo
    }
}