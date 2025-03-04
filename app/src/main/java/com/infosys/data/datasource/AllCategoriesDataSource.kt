package com.infosys.data.datasource

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.remote.FoodService
import retrofit2.Response
import javax.inject.Inject

class AllCategoriesDataSource @Inject constructor(var service: FoodService) {
    suspend fun getAllCategories(): Response<CategoryResponse> {
        return service.allCategory()
    }
}