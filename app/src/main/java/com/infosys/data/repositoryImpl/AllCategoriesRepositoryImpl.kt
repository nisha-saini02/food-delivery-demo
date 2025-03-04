package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.AllCategoriesDataSource
import com.infosys.data.model.category.CategoryResponse
import com.infosys.domain.repository.AllCategoriesRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response
import javax.inject.Inject

class AllCategoriesRepositoryImpl @Inject constructor(var dataSource: AllCategoriesDataSource): AllCategoriesRepository {
    override suspend fun getAllCategories(): Flow<Response<CategoryResponse>> {
        return channelFlow {
            val result = dataSource.getAllCategories()
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }
}