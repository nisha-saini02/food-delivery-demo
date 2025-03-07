package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.model.user.User
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import javax.inject.Inject

class WriteUserInfoLocalRepositoryImpl @Inject constructor(var store: MyDataStore): WriteUserInfoLocalRepository {
    override suspend fun writeUserInfo(userInfo: User) {
        store.writeUserInfo(userInfo)
    }
}