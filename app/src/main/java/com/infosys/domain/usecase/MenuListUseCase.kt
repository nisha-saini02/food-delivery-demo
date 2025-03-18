package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.MenusAndSubCategoryDetailsRepository

open class MenuListUseCase(var repository: MenusAndSubCategoryDetailsRepository) {
    suspend fun getMenuList(search: String): Resource<SubCategoryDetailsResponse?>
    = repository.getMenuList(search)
}