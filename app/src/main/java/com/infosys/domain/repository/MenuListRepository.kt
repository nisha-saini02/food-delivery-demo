package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface MenuListRepository {
    suspend fun getMenuList(search: String): Flow<Resource<SubCategoryDetailsResponse?>>
}