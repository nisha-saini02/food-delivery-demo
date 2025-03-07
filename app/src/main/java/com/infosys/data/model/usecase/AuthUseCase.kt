package com.infosys.data.model.usecase

import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.ReadUserInfoLocalUseCase
import com.infosys.domain.usecase.WriteUserInfoLocalUseCase
import javax.inject.Inject

data class AuthUseCase @Inject constructor(
    var readUserInfoLocalUseCase: ReadUserInfoLocalUseCase,
    var writeUserInfoLocalUseCase: WriteUserInfoLocalUseCase,
    var clearUserInfoLocalUseCase: ClearUserInfoLocalUseCase,
)