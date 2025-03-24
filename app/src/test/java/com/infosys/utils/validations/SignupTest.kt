package com.infosys.utils.validations

import com.infosys.utils.enums.SignUpValidation
import org.junit.Assert
import org.junit.Test

class SignupTest {

    @Test
    fun `signup with all fields empty returns FIELD_IS_EMPTY`() {
        val result = signup("", "", "", true)
        Assert.assertEquals(SignUpValidation.FIELD_IS_EMPTY, result)
    }

    @Test
    fun `signup with some fields empty returns FIELD_IS_EMPTY`() {
        val result = signup("John Doe", "", "Password123", true)
        Assert.assertEquals(SignUpValidation.FIELD_IS_EMPTY, result)
    }

    @Test
    fun `signup with terms not read returns TERM_AND_CONDITION_NOT_READ`() {
        val result = signup("John Doe", "john.doe@example.com", "Password123", false)
        Assert.assertEquals(SignUpValidation.TERM_AND_CONDITION_NOT_READ, result)
    }

    @Test
    fun `signup with invalid password returns PASSWORD_NOT_CORRECT`() {
        val result = signup("John Doe", "john.doe@example.com", "weak", true)
        Assert.assertEquals(SignUpValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signup with invalid email returns EMAIL_NOT_CORRECT`() {
        val result = signup("John Doe", "invalid-email", "Password123!", true)
        Assert.assertEquals(SignUpValidation.EMAIL_NOT_CORRECT, result)
    }

    @Test
    fun `signup with valid data returns VALIDATE`() {
        val result = signup("John Doe", "john.doe@example.com", "Password123!", true)
        Assert.assertEquals(SignUpValidation.VALIDATE, result)
    }
    @Test
    fun `signup with valid data and special character in name returns VALIDATE`() {
        val result = signup("John Doe!", "john.doe@example.com", "Password123!", true)
        Assert.assertEquals(SignUpValidation.VALIDATE, result)
    }
    @Test
    fun `signup with valid data and number in name returns VALIDATE`() {
        val result = signup("John Doe123", "john.doe@example.com", "Password123!", true)
        Assert.assertEquals(SignUpValidation.VALIDATE, result)
    }
    @Test
    fun `signup with valid data and uppercase in name returns VALIDATE`() {
        val result = signup("JOHN DOE", "john.doe@example.com", "Password123!", true)
        Assert.assertEquals(SignUpValidation.VALIDATE, result)
    }

}