package com.infosys.presenters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.resources.Resource
import com.infosys.data.model.category.Category
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.domain.usecase.LocalUseCase
import com.infosys.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val useCase: UseCase,
    val localUseCase: LocalUseCase,
): ViewModel() {
    private var _categories = MutableStateFlow<Resource<List<Category>?>>(Resource.Loading())
    //StateFlow: observable data holder classes; emit the new & update state to collector; holds only last known state
    val categories: StateFlow<Resource<List<Category>?>> = _categories

    private var _subcategories = MutableStateFlow<Resource<List<SubCategory>?>>(Resource.Loading())
    val subcategories: StateFlow<Resource<List<SubCategory>?>> = _subcategories
    val category = MutableStateFlow("")

    private var _subcategoriesDetails = MutableStateFlow<Resource<List<SubCategoryDetails>?>>(Resource.Loading())
    val subcategoriesDetails: StateFlow<Resource<List<SubCategoryDetails>?>> = _subcategoriesDetails

    private var _meals = MutableStateFlow<Resource<List<SubCategoryDetails>?>>(Resource.Loading())
    val meals: StateFlow<Resource<List<SubCategoryDetails>?>> = _meals

    fun getAllCategories() {
        viewModelScope.launch {
            _categories.value= Resource.Loading()
            if (_categories.value.data.isNullOrEmpty())
                useCase.getAllCategories()
                    .onStart {  }
                    .catch {
                        _categories.value = Resource.Error(it.message.toString())
                    }
                    .collect {
                        _categories.value = if (it.isSuccessful) {
                            Resource.Success(it.body()?.categories)
                        } else {
                            Resource.Error(it.message())
                        }
                    }
        }
    }

    fun getSubCategories(category: String) {
        viewModelScope.launch {
            _subcategories.value= Resource.Loading()
            useCase.getSubCategories(category)
                .catch {
                    _subcategories.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _subcategories.value = if (it.isSuccessful) {
                        Resource.Success(it.body()?.meals)
                    } else {
                        Resource.Error(it.message())
                    }
                }
        }
    }

    fun getSubCategoryDetails(subcategoryId: String) {
        viewModelScope.launch {
            _subcategoriesDetails.value= Resource.Loading()
            useCase.getSubCategoryDetails(subcategoryId)
                .catch {
                    _subcategoriesDetails.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _subcategoriesDetails.value = if (it.isSuccessful) {
                        Resource.Success(it.body()?.meals)
                    } else {
                        Resource.Error(it.message())
                    }
                }
        }
    }

    fun getMenuList(search: String = "") {
        viewModelScope.launch {
            _meals.value= Resource.Loading()
            useCase.getMenuList(search)
                .catch {
                    _meals.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _meals.value = if (it.isSuccessful) {
                        Resource.Success(it.body()?.meals)
                    } else {
                        Resource.Error(it.message())
                    }
                }
        }
    }
}