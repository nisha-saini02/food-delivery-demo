package com.infosys.data.datasource

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import retrofit2.Response
import javax.inject.Inject

class SubCategoryDetailsDataSource @Inject constructor(var service: FoodService) {
    suspend fun getSubCategoryDetails(subcategoryId: String): Response<SubCategoryDetailsResponse> {
        return service.getSubCategoryDetails(subcategoryId)
    }
}