package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository

open class SubCategoriesUseCase(var repository: AllCategoriesAndSubCategoriesRepository) {
    suspend fun getSubCategories(category: String): Resource<SubCategoryResponse?>
    = repository.getSubCategories(category)
}