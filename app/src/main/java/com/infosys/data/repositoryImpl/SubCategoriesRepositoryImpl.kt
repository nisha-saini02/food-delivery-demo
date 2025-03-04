package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.SubCategoriesDataSource
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.domain.repository.SubCategoriesRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response
import javax.inject.Inject

class SubCategoriesRepositoryImpl @Inject constructor(var dataSource: SubCategoriesDataSource): SubCategoriesRepository {
    override suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>> {
        return channelFlow {
            val result = dataSource.getSubCategories(category)
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }
}