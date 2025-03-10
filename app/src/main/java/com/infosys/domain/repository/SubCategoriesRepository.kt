package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface SubCategoriesRepository {
    suspend fun getSubCategories(category: String): Flow<Resource<SubCategoryResponse?>>
}