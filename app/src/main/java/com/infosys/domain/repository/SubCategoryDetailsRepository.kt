package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SubCategoryDetailsRepository {
    suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Response<SubCategoryDetailsResponse>>
}