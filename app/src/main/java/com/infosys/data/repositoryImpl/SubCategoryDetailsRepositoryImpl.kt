package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.SubCategoryDetailsRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class SubCategoryDetailsRepositoryImpl @Inject constructor(var service: FoodService): SubCategoryDetailsRepository {
    override suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Resource<SubCategoryDetailsResponse?>> {
        return channelFlow {
            val result = service.getSubCategoryDetails(subcategoryId)
            if (result.isSuccessful) {
                trySend(Resource.Success(result.body()))
                    .onFailure {
                        send(Resource.Error(it?.message.toString()))
                    }
            } else {
                send(Resource.Error(result.message()))
            }
        }
    }
}