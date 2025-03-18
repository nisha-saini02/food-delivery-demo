package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.MenusAndSubCategoryDetailsRepository

open class SubCategoryDetailsUseCase(var repository: MenusAndSubCategoryDetailsRepository) {
    suspend fun getSubCategoryDetails(subcategoryId: String): Resource<SubCategoryDetailsResponse?>
    = repository.getSubCategoryDetails(subcategoryId)
}