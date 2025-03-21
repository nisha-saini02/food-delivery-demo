package com.infosys.utils.validations

import com.infosys.utils.Constants.emailRegex
import com.infosys.utils.Constants.passwordRegex
import com.infosys.utils.enums.SignInValidation

fun signIn(email: String, password: String): SignInValidation {
    return if (email.isNotEmpty() && password.isNotEmpty()) {
        if (!password.matches(passwordRegex)) {
            SignInValidation.PASSWORD_NOT_CORRECT
        } else if (!email.matches(emailRegex)) {
            SignInValidation.EMAIL_NOT_CORRECT
        }
        else {
            SignInValidation.VALIDATE
        }
    } else {
        SignInValidation.FIELD_IS_EMPTY
    }
}