package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MenuListRepository {
    suspend fun getMenuList(search: String): Flow<Response<SubCategoryDetailsResponse>>
}