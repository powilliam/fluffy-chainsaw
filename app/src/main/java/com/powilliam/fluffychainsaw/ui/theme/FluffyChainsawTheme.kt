package com.powilliam.fluffychainsaw.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun FluffyChainsawTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    isDynamicColorSchemeSupported: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        isDark and isDynamicColorSchemeSupported -> dynamicDarkColorScheme(context)
        !isDark and isDynamicColorSchemeSupported -> dynamicLightColorScheme(context)
        isDark and !isDynamicColorSchemeSupported -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(colorScheme, content = content)
}