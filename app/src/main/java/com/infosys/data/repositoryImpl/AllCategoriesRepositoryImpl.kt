package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.domain.repository.AllCategoriesRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response
import javax.inject.Inject

class AllCategoriesRepositoryImpl @Inject constructor(var service: FoodService): AllCategoriesRepository {
    override suspend fun getAllCategories(): Flow<Response<CategoryResponse>> {
        return channelFlow {
            val result = service.allCategory()
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }
}