package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SubCategoriesRepository {
    suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>>
}