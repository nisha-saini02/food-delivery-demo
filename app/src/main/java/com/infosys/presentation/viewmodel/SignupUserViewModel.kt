package com.infosys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.model.user.User
import com.infosys.domain.usecase.WriteUserInfoLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SignupUserViewModel @Inject constructor(
    private var writeUserInfoLocalUseCase: WriteUserInfoLocalUseCase
): ViewModel() {

    fun writeUserInfo(userInfo: User) {
        viewModelScope.launch {
            writeUserInfoLocalUseCase.writeUserInfo(userInfo)
        }
    }
}