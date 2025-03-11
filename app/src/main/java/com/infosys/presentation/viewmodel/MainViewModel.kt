package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.usecase.RemoteUseCase
import com.infosys.data.remote.Resource
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val useCase: RemoteUseCase
): ViewModel() {

    private var _categories = MutableStateFlow<Resource<CategoryResponse?>>(Resource.Loading())
    //StateFlow: observable data holder classes; emit the new & update state to collector; holds only last known state
    val categories: StateFlow<Resource<CategoryResponse?>> = _categories

    private var _subcategories = MutableStateFlow<Resource<SubCategoryResponse?>>(Resource.Loading())
    val subcategories: StateFlow<Resource<SubCategoryResponse?>> = _subcategories
    val category = MutableStateFlow("")

    private var _subcategoriesDetails = MutableStateFlow<Resource<SubCategoryDetailsResponse?>>(Resource.Loading())
    val subcategoriesDetails: StateFlow<Resource<SubCategoryDetailsResponse?>> = _subcategoriesDetails

    private var _meals = MutableStateFlow<Resource<SubCategoryDetailsResponse?>>(Resource.Loading())
    val meals: StateFlow<Resource<SubCategoryDetailsResponse?>> = _meals

    fun getAllCategories() {
        viewModelScope.launch {
            _categories.value= Resource.Loading()
            try {
                useCase.allCategoriesUseCase.getAllCategories()
                    .collect {
                        _categories.value = it
                    }
            } catch (e: Exception) {
                _categories.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getSubCategories(category: String) {
        viewModelScope.launch {
            _subcategories.value= Resource.Loading()
            try {
                useCase.subCategoriesUseCase.getSubCategories(category)
                    .collect {
                        _subcategories.value = it
                    }
            } catch (e: Exception) {
                _subcategories.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getSubCategoryDetails(subcategoryId: String) {
        viewModelScope.launch {
            _subcategoriesDetails.value= Resource.Loading()
            try {
                useCase.subCategoryDetailsUseCase.getSubCategoryDetails(subcategoryId)
                    .collect {
                        _subcategoriesDetails.value = it
                    }
            } catch (e: Exception) {
                _subcategoriesDetails.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getMenuList(search: String = "") {
        viewModelScope.launch {
            _meals.value= Resource.Loading()
            try {
                useCase.menuListUseCase.getMenuList(search)
                    .collect {
                        _meals.value = it
                    }
            } catch (e: Exception) {
                _meals.value = Resource.Error(e.message.toString())
            }
        }
    }
}