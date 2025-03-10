package com.infosys.domain.usecase

import com.infosys.domain.repository.AllCategoriesRepository
import javax.inject.Inject

class AllCategoriesUseCase @Inject constructor(var repository: AllCategoriesRepository) {
    suspend fun getAllCategories() = repository.getAllCategories()
}