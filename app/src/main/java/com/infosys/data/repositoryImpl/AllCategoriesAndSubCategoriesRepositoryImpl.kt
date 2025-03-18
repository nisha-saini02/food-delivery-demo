package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository

class AllCategoriesAndSubCategoriesRepositoryImpl(var service: FoodService): AllCategoriesAndSubCategoriesRepository {
    override suspend fun getAllCategories(): Resource<CategoryResponse?> {
        return try {
            val result = service.allCategory()
            if (result.isSuccessful)
                Resource.Success(result.body())
            else
                Resource.Error(result.message())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getSubCategories(category: String): Resource<SubCategoryResponse?> {
        return try {
            val result = service.getSubCategories(category)
            if (result.isSuccessful)
                Resource.Success(result.body())
            else
                Resource.Error(result.message())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}