package com.infosys.domain.usecase

import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.domain.repository.MenuListRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MenuListUseCase @Inject constructor(var repository: MenuListRepository) {
    suspend fun getMenuList(search: String): Flow<Response<SubCategoryDetailsResponse>>
            = repository.getMenuList(search)
}