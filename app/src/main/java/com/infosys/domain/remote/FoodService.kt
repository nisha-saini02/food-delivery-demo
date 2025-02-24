package com.infosys.domain.remote

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("categories.php")
    suspend fun allCategory(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getSubCategories(
        @Query("c") category: String
    ): Response<SubCategoryResponse>

    @GET("lookup.php")
    suspend fun getSubCategoryDetails(
        @Query("i") subcategoryId: String
    ): Response<SubCategoryDetailsResponse>
}
