package com.powilliam.fluffychainsaw.ui.utils

import java.text.NumberFormat

fun currency(
    value: Float,
    formatter: NumberFormat = NumberFormat.getCurrencyInstance()
        .also { instance -> instance.maximumFractionDigits = 2 }
): String {
    return try {
        formatter.format(value)
    } catch (_: NumberFormatException) {
        return "$value"
    }
}