package com.infosys.utils

import org.junit.Test

class TaskUtilTest {
    @Test
    fun getVisaCardType() {
        val cardNumber = "4111111111111111"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Visa")
    }

    @Test
    fun getMasterCardCardType() {
        val cardNumber = "5555555555554444"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Master\nCard")
    }

    @Test
    fun getAmericanExpressCardType() {
        val cardNumber = "378282246310005"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "American\nExpress")
    }

    @Test
    fun getDiscoverCardType() {
        val cardNumber = "6011111111111117"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Discover")
    }

    @Test
    fun getDinersClubCardType() {
        val cardNumber = "36381828382736"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Diners\nClub\nInternational")
    }

    @Test
    fun getJcbCardType() {
        val cardNumber = "35301113321234"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "JCB")
    }

    @Test
    fun getUnionPayCardType() {
        val cardNumber = "6212345678901234"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "UnionPay")
    }

    @Test
    fun getCarteBlancheCardType() {
        val cardNumber = "30134876876754"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Carte\nBlanche")
    }

    @Test
    fun getMaestroCardType() {
        val cardNumber = "693847563838"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "Maestro")
    }

    @Test
    fun getRuPayCardType() {
        val cardNumber = "8284763847563838"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == "RuPay")
    }

    @Test
    fun getEmptyCardType() {
        val cardNumber = ""
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == null)
    }

    @Test
    fun getNullCardType() {
        val cardNumber = "1234567890"
        val cardType = TaskUtil.getCardType(cardNumber)
        assert(cardType == null)
    }
}