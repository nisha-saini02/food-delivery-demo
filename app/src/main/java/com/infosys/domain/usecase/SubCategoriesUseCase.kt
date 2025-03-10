package com.infosys.domain.usecase

import com.infosys.domain.repository.SubCategoriesRepository
import javax.inject.Inject

class SubCategoriesUseCase @Inject constructor(var repository: SubCategoriesRepository) {
    suspend fun getSubCategories(category: String) = repository.getSubCategories(category)
}