package com.infosys.data.datasource

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import retrofit2.Response
import javax.inject.Inject

class MenuDataSource @Inject constructor(var service: FoodService) {
    suspend fun getMenuList(search: String): Response<SubCategoryDetailsResponse> {
        return service.getMenuList(search)
    }
}