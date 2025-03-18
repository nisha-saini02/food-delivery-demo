package com.infosys.domain.repository

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.Resource

interface AllCategoriesAndSubCategoriesRepository {
    suspend fun getAllCategories(): Resource<CategoryResponse?>
    suspend fun getSubCategories(category: String): Resource<SubCategoryResponse?>
}