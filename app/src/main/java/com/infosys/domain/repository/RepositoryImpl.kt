package com.infosys.domain.repository

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.datasource.DataSource
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response

class RepositoryImpl(private val dataSource: DataSource): Repository {
    override suspend fun getAllCategories(): Flow<Response<CategoryResponse>> {
        return channelFlow {
            val result = dataSource.getAllCategories()
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }

    override suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>> {
        return channelFlow {
            val result = dataSource.getSubCategories(category)
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }

    override suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Response<SubCategoryDetailsResponse>> {
        return channelFlow {
            val result = dataSource.getSubCategoryDetails(subcategoryId)
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }
}