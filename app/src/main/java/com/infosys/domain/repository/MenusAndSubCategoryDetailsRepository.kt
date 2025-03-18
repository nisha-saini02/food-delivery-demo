package com.infosys.domain.repository

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource

interface MenusAndSubCategoryDetailsRepository {
    suspend fun getSubCategoryDetails(subcategoryId: String): Resource<SubCategoryDetailsResponse?>
    suspend fun getMenuList(search: String): Resource<SubCategoryDetailsResponse?>
}