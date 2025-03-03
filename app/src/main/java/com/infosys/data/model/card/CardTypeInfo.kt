package com.infosys.data.model.card

data class CardTypeInfo(val name: String, val digitLengths: List<Int>, val description: String)

val CardTypeInfos = listOf(
    CardTypeInfo("Visa", listOf(13, 16), "4"),
    CardTypeInfo("Master\nCard", listOf(16), "51-55 or 2221-2720"),
    CardTypeInfo("American\nExpress", listOf(15), "34 or 37"),
    CardTypeInfo("Discover", listOf(16), "6011, 622126–622925, 64, 65"),
    CardTypeInfo("Diners\nClub\nInternational", listOf(14), "36"),
    CardTypeInfo("JCB", listOf(16), "3528-3589"),
    CardTypeInfo("UnionPay", listOf(16, 17, 18, 19), "62"),
    CardTypeInfo("Carte\nBlanche", listOf(14), "300-305"),
    CardTypeInfo("Maestro", listOf(12, 13, 14, 15, 16, 17, 18, 19), "50, 56-69"),
    CardTypeInfo("RuPay", listOf(16), "60, 65, 81, 82")
)

fun getCardType(cardNumber: String): String? {
    for (cardType in CardTypeInfos) {
        if (!cardType.digitLengths.contains(cardNumber.length)) {
            continue
        }

        for (prefix in cardType.description.split(" or ", ", ").map { it.trim() }) {
            val prefixRange = prefix.trim()

            if (prefixRange.contains("-")) {
                val (start, end) = prefixRange.split("-").map { it.toInt() }
                if (cardNumber.take(start.toString().length).toInt() in start..end) {
                    return cardType.name
                }
            } else if (cardNumber.startsWith(prefixRange)) {
                return cardType.name
            }
        }
    }
    return null  // If no match is found
}