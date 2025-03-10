package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.SubCategoriesRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class SubCategoriesRepositoryImpl @Inject constructor(var service: FoodService): SubCategoriesRepository {
    override suspend fun getSubCategories(category: String): Flow<Resource<SubCategoryResponse?>> {
        return channelFlow {
            val result = service.getSubCategories(category)
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