package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.usecase.AuthUseCase
import com.infosys.data.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(var useCase: AuthUseCase): ViewModel() {

    private var _userInfo = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = _userInfo

    fun writeUserInfo(userInfo: User) {
        viewModelScope.launch {
            useCase.writeUserInfoLocalUseCase.writeUserInfo(userInfo)
        }
    }

    fun readUserInfo() {
        viewModelScope.launch {
            useCase.readUserInfoLocalUseCase.getUserInfo()
                .catch {
                    _userInfo.value = User()
                }
                .collect {
                    _userInfo.value = it
                }
        }
    }

    fun clearUserInfo() {
        viewModelScope.launch {
            useCase.clearUserInfoLocalUseCase.clearUserInfo()
        }
    }
}