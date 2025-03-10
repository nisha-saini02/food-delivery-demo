package com.infosys.domain.usecase

import com.infosys.domain.repository.SubCategoryDetailsRepository
import javax.inject.Inject

class SubCategoryDetailsUseCase @Inject constructor(var repository: SubCategoryDetailsRepository) {
    suspend fun getSubCategoryDetails(subcategoryId: String)
            = repository.getSubCategoryDetails(subcategoryId)
}