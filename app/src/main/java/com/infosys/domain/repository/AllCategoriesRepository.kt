package com.infosys.domain.repository

import com.infosys.data.model.category.CategoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AllCategoriesRepository {
    suspend fun getAllCategories(): Flow<Response<CategoryResponse>>
}