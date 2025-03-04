package com.infosys.utils.validations

import com.infosys.data.model.card.getCardType
import com.infosys.data.model.enums.CheckoutValidation
import java.util.Calendar

fun checkout(name: String, account: String, month: String, year: String, cvv: String): CheckoutValidation {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString().takeLast(2)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

    return if (name.isNotEmpty() && account.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty() && cvv.isNotEmpty()) {
        if (year < currentYear) {
            CheckoutValidation.ENTER_VALID_YEAR
        } else if (month.toInt() !in 1..12) {
            CheckoutValidation.ENTER_VALID_MONTH
        } else if (year == currentYear && month.toInt() <= (currentMonth+1)) {
            CheckoutValidation.ENTER_VALID_MONTH
        } else if (cvv.length != 3) {
            CheckoutValidation.INVALID_CVV
        } else if (getCardType(account) == null) {
            CheckoutValidation.ENTER_VALID_ACCOUNT_NUMBER
        } else {
            CheckoutValidation.VALIDATE
        }
    } else {
        CheckoutValidation.FIELDS_ARE_EMPTY
    }
}