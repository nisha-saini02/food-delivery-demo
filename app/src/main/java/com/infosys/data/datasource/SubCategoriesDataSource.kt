package com.infosys.data.datasource

import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.FoodService
import retrofit2.Response
import javax.inject.Inject

class SubCategoriesDataSource @Inject constructor(var service: FoodService) {
    suspend fun getSubCategories(category: String): Response<SubCategoryResponse> {
        return service.getSubCategories(category)
    }
}