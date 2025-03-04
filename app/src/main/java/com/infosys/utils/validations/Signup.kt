package com.infosys.utils.validations

import com.infosys.presentation.enums.SignUpValidation
import com.infosys.utils.Constants.emailRegex
import com.infosys.utils.Constants.passwordRegex

fun signup(name: String, email: String, password: String, termRead: Boolean): SignUpValidation {
    return if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && termRead) {
        if (!password.matches(passwordRegex)) {
            SignUpValidation.PASSWORD_NOT_CORRECT
        } else if (!email.matches(emailRegex)) {
            SignUpValidation.EMAIL_NOT_CORRECT
        }
        else {
            SignUpValidation.VALIDATE
        }
    } else if(!termRead) {
        SignUpValidation.TERM_AND_CONDITION_NOT_READ
    } else {
        SignUpValidation.FIELD_IS_EMPTY
    }
}