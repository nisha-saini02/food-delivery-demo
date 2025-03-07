package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.MyDataStore
import com.infosys.domain.repository.ClearUserInfoLocalRepository
import javax.inject.Inject

class ClearUserInfoLocalRepositoryImpl @Inject constructor(var store: MyDataStore) : ClearUserInfoLocalRepository {
    override suspend fun clearUserInfo() {
        store.clearUserInfo()
    }
}