package com.infosys.domain.repository

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getAllCategories(): Flow<Response<CategoryResponse>>
    suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>>
    suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Response<SubCategoryDetailsResponse>>
}