package com.infosys.utils.validations

import com.infosys.utils.enums.SignInValidation
import org.junit.Assert
import org.junit.Test

class SigninTest {

    @Test
    fun `signIn with valid email and password returns VALIDATE`() {
        val email = "test@example.com"
        val password = "Password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.VALIDATE, result)
    }

    @Test
    fun `signIn with empty email and password returns FIELD_IS_EMPTY`() {
        val email = ""
        val password = ""
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.FIELD_IS_EMPTY, result)
    }

    @Test
    fun `signIn with empty email returns FIELD_IS_EMPTY`() {
        val email = ""
        val password = "Password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.FIELD_IS_EMPTY, result)
    }

    @Test
    fun `signIn with empty password returns FIELD_IS_EMPTY`() {
        val email = "test@example.com"
        val password = ""
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.FIELD_IS_EMPTY, result)
    }

    @Test
    fun `signIn with invalid password returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "password" // Missing uppercase, number, and special character
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with invalid email returns EMAIL_NOT_CORRECT`() {
        val email = "invalid-email"
        val password = "Password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.EMAIL_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with invalid email and password returns PASSWORD_NOT_CORRECT`() {
        val email = "invalid-email"
        val password = "password"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with password without uppercase returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with password without lowercase returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "PASSWORD123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with password without number returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "Password@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with password without special character returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "Password123"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with password less than 8 characters returns PASSWORD_NOT_CORRECT`() {
        val email = "test@example.com"
        val password = "Pass1@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.PASSWORD_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with email without @ returns EMAIL_NOT_CORRECT`() {
        val email = "testexample.com"
        val password = "Password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.EMAIL_NOT_CORRECT, result)
    }

    @Test
    fun `signIn with email without domain returns EMAIL_NOT_CORRECT`() {
        val email = "test@example"
        val password = "Password123@"
        val result = signIn(email, password)
        Assert.assertEquals(SignInValidation.EMAIL_NOT_CORRECT, result)
    }

}