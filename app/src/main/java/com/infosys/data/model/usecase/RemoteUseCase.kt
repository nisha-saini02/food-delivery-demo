package com.infosys.data.model.usecase

import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.MenuListUseCase
import com.infosys.domain.usecase.SubCategoriesUseCase
import com.infosys.domain.usecase.SubCategoryDetailsUseCase
import javax.inject.Inject

class RemoteUseCase @Inject constructor(
    var allCategoriesUseCase: AllCategoriesUseCase,
    var subCategoriesUseCase: SubCategoriesUseCase,
    var subCategoryDetailsUseCase: SubCategoryDetailsUseCase,
    var menuListUseCase: MenuListUseCase
)