package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import com.infosys.data.remote.Resource
import com.infosys.domain.usecase.MenuListUseCase
import com.infosys.domain.usecase.SubCategoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val subCategoryDetailsUseCase: SubCategoryDetailsUseCase,
    private val menuListUseCase: MenuListUseCase
): ViewModel() {

    private var _subcategoriesDetails = MutableStateFlow<Resource<SubCategoryDetailsResponse?>>(
        Resource.Loading())
    val subcategoriesDetails: StateFlow<Resource<SubCategoryDetailsResponse?>> = _subcategoriesDetails

    private var _meals = MutableStateFlow<Resource<SubCategoryDetailsResponse?>>(Resource.Loading())
    val meals: StateFlow<Resource<SubCategoryDetailsResponse?>> = _meals

    fun getSubCategoryDetails(subcategoryId: String) {
        viewModelScope.launch {
            _subcategoriesDetails.value = Resource.Loading()
            try {
                _subcategoriesDetails.value = subCategoryDetailsUseCase.getSubCategoryDetails(subcategoryId)
            } catch (e: Exception) {
                _subcategoriesDetails.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getMenuList(search: String = "") {
        viewModelScope.launch {
            _meals.value= Resource.Loading()
            try {
                _meals.value = menuListUseCase.getMenuList(search)
            } catch (e: Exception) {
                _meals.value = Resource.Error(e.message.toString())
            }
        }
    }
}