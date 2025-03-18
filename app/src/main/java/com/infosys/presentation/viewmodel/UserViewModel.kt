package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.user.User
import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.ReadUserInfoLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class UserViewModel @Inject constructor(
    private var readUserInfoLocalUseCase: ReadUserInfoLocalUseCase,
    private var clearUserInfoLocalUseCase: ClearUserInfoLocalUseCase,
): ViewModel() {

    private var _userInfo = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = _userInfo

    fun readUserInfo() {
        viewModelScope.launch {
            try {
                readUserInfoLocalUseCase.getUserInfo()
                    .collect {
                        if (it == null) {
                            _userInfo.value = null
                            return@collect
                        }
                        _userInfo.value = it
                    }
            } catch (e: Exception) {
                _userInfo.value = null
            }
        }
    }

    fun clearUserInfo() {
        viewModelScope.launch {
            clearUserInfoLocalUseCase.clearUserInfo()
        }
    }
}