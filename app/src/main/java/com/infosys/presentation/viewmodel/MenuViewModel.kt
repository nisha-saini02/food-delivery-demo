package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.remote.Resource
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategoryResponse
import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.SubCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MenuViewModel @Inject constructor(
    private val allCategoriesUseCase: AllCategoriesUseCase,
    private val subCategoriesUseCase: SubCategoriesUseCase
): ViewModel() {

    private var _categories = MutableStateFlow<CategoryResponse?>(null)
    //StateFlow: observable data holder classes; emit the new & update state to collector; holds only last known state
    val categories: StateFlow<CategoryResponse?> = _categories

    private var _subcategories = MutableStateFlow<SubCategoryResponse?>(null)
    val subcategories: StateFlow<SubCategoryResponse?> = _subcategories
    val category = MutableStateFlow("")

    fun getAllCategories() {
        viewModelScope.launch {
            try {
                when(allCategoriesUseCase.getAllCategories()) {
                    is Resource.Success -> {
                        _categories.value = allCategoriesUseCase.getAllCategories().data
                    }
                    else -> {
                        _categories.value = null
                    }
                }
            } catch (e: Exception) {
                _categories.value = null
            }
        }
    }

    fun getSubCategories(category: String) {
        viewModelScope.launch {
            _subcategories.value = null
            try {
                when (subCategoriesUseCase.getSubCategories(category)) {
                    is Resource.Success -> {
                        _subcategories.value = subCategoriesUseCase.getSubCategories(category).data
                    }
                    else -> {
                        _subcategories.value = null
                    }
                }
            } catch (e: Exception) {
                _subcategories.value = null
            }
        }
    }
}