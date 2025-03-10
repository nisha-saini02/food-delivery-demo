package com.infosys.domain.repository

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface AllCategoriesRepository {
    suspend fun getAllCategories(): Flow<Resource<CategoryResponse?>>
}