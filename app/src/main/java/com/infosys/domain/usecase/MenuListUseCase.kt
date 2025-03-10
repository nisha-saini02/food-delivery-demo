package com.infosys.domain.usecase

import com.infosys.domain.repository.MenuListRepository
import javax.inject.Inject

class MenuListUseCase @Inject constructor(var repository: MenuListRepository) {
    suspend fun getMenuList(search: String) = repository.getMenuList(search)
}