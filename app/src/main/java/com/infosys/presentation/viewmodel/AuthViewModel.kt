package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.usecase.AuthUseCase
import com.infosys.data.model.user.User
import com.infosys.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(var useCase: AuthUseCase): ViewModel() {

    private var _userInfo = MutableStateFlow<Resource<User?>>(Resource.Loading())
    val userInfo: StateFlow<Resource<User?>> = _userInfo

    fun writeUserInfo(userInfo: User) {
        viewModelScope.launch {
            useCase.writeUserInfoLocalUseCase.writeUserInfo(userInfo)
        }
    }

    fun readUserInfo() {
        viewModelScope.launch {
            _userInfo.value = Resource.Loading()
            try {
                useCase.readUserInfoLocalUseCase.getUserInfo()
                    .collect {
                        if (it == null) {
                            _userInfo.value = Resource.Error("Data not present")
                            return@collect
                        }
                        _userInfo.value = Resource.Success(it)
                    }
            } catch (e: Exception) {
                _userInfo.value = Resource.Error(e.message ?: "Unknown exception")
            }
        }
    }

    fun clearUserInfo() {
        viewModelScope.launch {
            useCase.clearUserInfoLocalUseCase.clearUserInfo()
        }
    }
}