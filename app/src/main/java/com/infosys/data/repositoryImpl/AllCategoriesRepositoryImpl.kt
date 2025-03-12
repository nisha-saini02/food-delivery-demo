package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class AllCategoriesRepositoryImpl @Inject constructor(var service: FoodService): AllCategoriesRepository {
    override suspend fun getAllCategories(): Flow<Resource<CategoryResponse?>> {
        return channelFlow {
            try {
                val result = service.allCategory()
                if (result.isSuccessful) {
                    trySend(Resource.Success(result.body()))
                } else {
                    send(Resource.Error(result.message()))
                }
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}