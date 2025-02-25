package com.infosys.domain.usecase

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UseCase (val repository: Repository) {
    suspend fun getAllCategories(): Flow<Response<CategoryResponse>>
            = repository.getAllCategories()

    suspend fun getSubCategories(category: String): Flow<Response<SubCategoryResponse>>
            = repository.getSubCategories(category)

    suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Response<SubCategoryDetailsResponse>>
            = repository.getSubCategoryDetails(subcategoryId)

    suspend fun getMenuList(search: String): Flow<Response<SubCategoryDetailsResponse>>
            = repository.getMenuList(search)
}