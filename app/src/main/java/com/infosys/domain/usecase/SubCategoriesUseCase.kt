package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.domain.repository.SubCategoriesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SubCategoriesUseCase @Inject constructor(var repository: SubCategoriesRepository) {
    suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>>
            = repository.getSubCategories(category)
}