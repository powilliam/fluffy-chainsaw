package com.powilliam.fluffychainsaw.ui.theme

import android.os.Build
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun FluffyChainsawTheme(
    isDynamicColorSchemeSupported: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    indication: Indication = rememberRipple(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = if (isDynamicColorSchemeSupported) {
        dynamicDarkColorScheme(context)
    } else {
        darkColorScheme()
    }

    MaterialTheme(colorScheme, NotoSansTypography) {
        CompositionLocalProvider(LocalIndication provides indication) {
            content()
        }
    }
}