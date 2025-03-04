package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.MenuDataSource
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.repository.MenuListRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Response
import javax.inject.Inject

class MenuListRepositoryImpl @Inject constructor(var dataSource: MenuDataSource): MenuListRepository {
    override suspend fun getMenuList(search: String): Flow<Response<SubCategoryDetailsResponse>> {
        return channelFlow {
            val result = dataSource.getMenuList(search)
            trySend(result)
                .onFailure {
                    send(Response.error(400, result.errorBody()))
                }
        }
    }
}