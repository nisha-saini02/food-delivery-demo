package com.infosys.utils

import com.infosys.utils.Constants.CardTypeInfos

object TaskUtil {
    fun getCardType(cardNumber: String): String? {
        if (cardNumber.isNotEmpty()) {
            for (cardType in CardTypeInfos) {
                for (prefix in cardType.description.split(" or ", ", ").map { it.trim() }) {
                    val prefixRange = prefix.trim()

                    if (prefixRange.contains("-")) {
                        val (start, end) = prefixRange.split("-").map { it.toInt() }
                        if (cardNumber.take(start.toString().length).toInt() in start..end)
                            return cardType.name
                    } else if (cardNumber.startsWith(prefixRange))
                        return cardType.name
                }
            }
            return null  // If no match is found
        } else return null
    }
}