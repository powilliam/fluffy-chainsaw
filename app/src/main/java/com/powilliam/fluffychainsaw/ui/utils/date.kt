package com.powilliam.fluffychainsaw.ui.utils

import kotlinx.datetime.*

fun daysUntil(
    epochInUtcMilliseconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    utcTimeZone: TimeZone = TimeZone.UTC
): Int {
    val monthEndingDateTime =
        Instant.fromEpochMilliseconds(epochInUtcMilliseconds).toLocalDateTime(utcTimeZone)
    val todayDateTime = Clock.System.now().toLocalDateTime(timeZone)
    return todayDateTime.date.daysUntil(monthEndingDateTime.date)
}