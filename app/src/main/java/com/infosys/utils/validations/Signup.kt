package com.infosys.utils.validations

import com.infosys.data.model.enums.SignUpValidation

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

val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,20}\$")
val emailRegex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")