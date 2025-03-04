package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.SubCategoryDetailsDataSource
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.repository.SubCategoryDetailsRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response
import javax.inject.Inject

class SubCategoryDetailsRepositoryImpl @Inject constructor(var dataSource: SubCategoryDetailsDataSource): SubCategoryDetailsRepository {
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