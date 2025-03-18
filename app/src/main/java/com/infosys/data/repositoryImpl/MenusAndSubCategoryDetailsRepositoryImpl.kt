package com.infosys.data.repositoryImpl

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.FoodService
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.MenusAndSubCategoryDetailsRepository

class MenusAndSubCategoryDetailsRepositoryImpl(var service: FoodService): MenusAndSubCategoryDetailsRepository {
    override suspend fun getSubCategoryDetails(subcategoryId: String): Resource<SubCategoryDetailsResponse?> {
        return try {
            val result = service.getSubCategoryDetails(subcategoryId)
            if (result.isSuccessful)
                Resource.Success(result.body())
            else
                Resource.Error(result.message())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getMenuList(search: String): Resource<SubCategoryDetailsResponse?> {
        return try {
            val result = service.getMenuList(search)
            if (result.isSuccessful)
                Resource.Success(result.body())
            else
                Resource.Error(result.message())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}