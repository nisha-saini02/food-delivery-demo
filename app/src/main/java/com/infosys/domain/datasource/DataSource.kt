package com.infosys.domain.datasource

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.remote.FoodService
import retrofit2.Response

class DataSource (val service: FoodService) {
    suspend fun getAllCategories(): Response<CategoryResponse> {
        return service.allCategory()
    }

    suspend fun getSubCategories(category: String): Response<SubCategoryResponse> {
        return service.getSubCategories(category)
    }

    suspend fun getSubCategoryDetails(subcategoryId: String): Response<SubCategoryDetailsResponse> {
        return service.getSubCategoryDetails(subcategoryId)
    }
}