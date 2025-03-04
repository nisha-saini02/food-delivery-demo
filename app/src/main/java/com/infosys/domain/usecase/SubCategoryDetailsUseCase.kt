package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.repository.SubCategoryDetailsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SubCategoryDetailsUseCase @Inject constructor(var repository: SubCategoryDetailsRepository) {
    suspend fun getSubCategoryDetails(subcategoryId: String): Flow<Response<SubCategoryDetailsResponse>>
            = repository.getSubCategoryDetails(subcategoryId)
}