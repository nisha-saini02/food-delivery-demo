package com.infosys.domain.usecase

import com.infosys.data.model.category.CategoryResponse
import com.infosys.domain.repository.AllCategoriesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AllCategoriesUseCase @Inject constructor(var repository: AllCategoriesRepository) {
    suspend fun getAllCategories(): Flow<Response<CategoryResponse>>
            = repository.getAllCategories()
}