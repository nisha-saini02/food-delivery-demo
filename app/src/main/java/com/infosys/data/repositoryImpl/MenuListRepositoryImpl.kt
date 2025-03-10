package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.MenuListRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class MenuListRepositoryImpl @Inject constructor(var service: FoodService): MenuListRepository {
    override suspend fun getMenuList(search: String): Flow<Resource<SubCategoryDetailsResponse?>> {
        return channelFlow {
            val result = service.getMenuList(search)
            if (result.isSuccessful) {
                trySend(Resource.Success(result.body()))
                    .onFailure {
                        send(Resource.Error(result.message()))
                    }
            } else {
                send(Resource.Error(result.errorBody().toString()))
            }
        }
    }
}