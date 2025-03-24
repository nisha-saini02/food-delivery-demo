package com.infosys.utils.validations

import com.infosys.utils.enums.CheckoutValidation
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CheckoutTest {

    @Test
    fun `checkout with all fields empty returns FIELDS_ARE_EMPTY`() {
        val result = checkout("", "", "", "", "")
        assertEquals(CheckoutValidation.FIELDS_ARE_EMPTY, result)
    }

    @Test
    fun `checkout with some fields empty returns FIELDS_ARE_EMPTY`() {
        val result = checkout("John Doe", "", "12", "25", "123")
        assertEquals(CheckoutValidation.FIELDS_ARE_EMPTY, result)
    }

    @Test
    fun `checkout with invalid year returns ENTER_VALID_YEAR`() {
        val currentYear = 25
        val result = checkout("John Doe", "1234567890123456", "12", (currentYear - 1).toString(), "123")
        assertEquals(CheckoutValidation.ENTER_VALID_YEAR, result)
    }

    @Test
    fun `checkout with future year beyond limit returns ENTER_VALID_YEAR`() {
        val currentYear = 25
        val result = checkout("John Doe", "1234567890123456", "12", (currentYear + 6).toString(), "123")
        assertEquals(CheckoutValidation.ENTER_VALID_YEAR, result)
    }

    @Test
    fun `checkout with invalid month returns ENTER_VALID_MONTH`() {
        val result = checkout("John Doe", "1234567890123456", "13", "25", "123")
        assertEquals(CheckoutValidation.ENTER_VALID_MONTH, result)
    }

    @Test
    fun `checkout with past month in current year returns ENTER_VALID_MONTH`() {
        val currentYear = 25
        val currentMonth = 3
        val result = checkout("John Doe", "1234567890123456", currentMonth.toString(), currentYear.toString(), "123")
        assertEquals(CheckoutValidation.ENTER_VALID_MONTH, result)
    }

    @Test
    fun `checkout with invalid cvv returns INVALID_CVV`() {
        val result = checkout("John Doe", "1234567890123456", "12", "25", "12")
        assertEquals(CheckoutValidation.INVALID_CVV, result)
    }

    @Test
    fun `checkout with invalid account number returns ENTER_VALID_ACCOUNT_NUMBER`() {
        val result = checkout("John Doe", "123", "12", "25", "123")
        assertEquals(CheckoutValidation.ENTER_VALID_ACCOUNT_NUMBER, result)
    }

    @Test
    fun `checkout with valid data returns VALIDATE`() {
        val result = checkout("John Doe", "4111111111111", "12", "25", "123")
        assertEquals(CheckoutValidation.VALIDATE, result)
    }

    @Test
    fun `checkout with valid data and current month returns VALIDATE`() {
        val currentYear = 27
        val currentMonth = 5
        val result = checkout("John Doe", "4111111111111", currentMonth.toString(), currentYear.toString(), "123")
        assertEquals(CheckoutValidation.VALIDATE, result)
    }

    @Test
    fun `checkout with valid data and next month returns VALIDATE`() {
        val currentYear = 29
        val currentMonth = 5
        val result = checkout("John Doe", "4111111111111", currentMonth.toString(), currentYear.toString(), "123")
        assertEquals(CheckoutValidation.VALIDATE, result)
    }

}