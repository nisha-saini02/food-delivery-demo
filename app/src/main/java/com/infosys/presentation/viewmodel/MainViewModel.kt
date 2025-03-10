package com.infosys.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.usecase.RemoteUseCase
import com.infosys.data.remote.Resource
import com.infosys.data.model.category.CategoryResponse
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val useCase: RemoteUseCase
): ViewModel() {
    private val TAG = MainViewModel::class.java.name

    private var _categories = MutableStateFlow<Resource<CategoryResponse?>>(Resource.Loading())
    //StateFlow: observable data holder classes; emit the new & update state to collector; holds only last known state
    val categories: StateFlow<Resource<CategoryResponse?>> = _categories

    private var _subcategories = MutableStateFlow<List<SubCategory>?>(listOf())
    val subcategories: StateFlow<List<SubCategory>?> = _subcategories
    val category = MutableStateFlow("")

    private var _subcategoriesDetails = MutableStateFlow<List<SubCategoryDetails>?>(listOf())
    val subcategoriesDetails: StateFlow<List<SubCategoryDetails>?> = _subcategoriesDetails

    private var _meals = MutableStateFlow<List<SubCategoryDetails>?>(listOf())
    val meals: StateFlow<List<SubCategoryDetails>?> = _meals

    fun getAllCategories() {
        viewModelScope.launch {
            _categories.value= Resource.Loading()
            useCase.allCategoriesUseCase.getAllCategories()
                .onStart {  }
                .catch {
                    _categories.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _categories.value = it
                }
        }
    }

    fun getSubCategories(category: String) {
        viewModelScope.launch {
            useCase.subCategoriesUseCase.getSubCategories(category)
                .catch {
                    _subcategories.value = null
                }
                .collect {
                    when(it) {
                        is Resource.Error -> {
                            Log.e(TAG, "getSubCategories: ${it.message}")
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            _subcategories.value = it.data?.meals
                        }
                    }
                }
        }
    }

    fun getSubCategoryDetails(subcategoryId: String) {
        viewModelScope.launch {
            useCase.subCategoryDetailsUseCase.getSubCategoryDetails(subcategoryId)
                .catch {
                    Log.e(TAG, "getSubCategoryDetails: ${it.message}")
                }
                .collect {
                    when(it) {
                        is Resource.Error -> {
                            Log.e(TAG, "getSubCategoryDetails: ${it.message}")
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            _subcategoriesDetails.value = it.data?.meals
                        }
                    }
                }
        }
    }

    fun getMenuList(search: String = "") {
        viewModelScope.launch {
            useCase.menuListUseCase.getMenuList(search)
                .catch {
                    Log.e(TAG, "getMenuList: ${it.message}")
                }
                .collect {
                    when(it) {
                        is Resource.Error -> {
                            Log.e(TAG, "getMenuList: ${it.message}")
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            _meals.value = it.data?.meals
                        }
                    }
                }
        }
    }
}