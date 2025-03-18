package com.infosys.domain.usecase

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository

open class AllCategoriesUseCase(var repository: AllCategoriesAndSubCategoriesRepository) {
    suspend fun getAllCategories(): Resource<CategoryResponse?>
    =  repository.getAllCategories()
}