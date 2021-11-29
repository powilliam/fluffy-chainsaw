package com.powilliam.fluffychainsaw.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.powilliam.fluffychainsaw.R

private val NotoSansFontFamily = FontFamily(
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_bold, FontWeight.Medium),
)

@OptIn(ExperimentalUnitApi::class)
val NotoSansTypography = Typography(
    displayLarge = TextStyle(
        fontSize = TextUnit(57F, TextUnitType.Sp),
        lineHeight = TextUnit(64F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = TextStyle(
        fontSize = TextUnit(45F, TextUnitType.Sp),
        lineHeight = TextUnit(52F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = TextStyle(
        fontSize = TextUnit(36F, TextUnitType.Sp),
        lineHeight = TextUnit(44F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontSize = TextUnit(32F, TextUnitType.Sp),
        lineHeight = TextUnit(40F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontSize = TextUnit(28F, TextUnitType.Sp),
        lineHeight = TextUnit(36F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontSize = TextUnit(24F, TextUnitType.Sp),
        lineHeight = TextUnit(32F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontSize = TextUnit(22F, TextUnitType.Sp),
        lineHeight = TextUnit(28F, TextUnitType.Sp),
        letterSpacing = TextUnit(0F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontSize = TextUnit(16F, TextUnitType.Sp),
        lineHeight = TextUnit(24F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.15F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontSize = TextUnit(14F, TextUnitType.Sp),
        lineHeight = TextUnit(20F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.1F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    labelLarge = TextStyle(
        fontSize = TextUnit(14F, TextUnitType.Sp),
        lineHeight = TextUnit(20F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.1F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontSize = TextUnit(12F, TextUnitType.Sp),
        lineHeight = TextUnit(16F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.5F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontSize = TextUnit(11F, TextUnitType.Sp),
        lineHeight = TextUnit(16F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.5F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = TextUnit(16F, TextUnitType.Sp),
        lineHeight = TextUnit(24F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.15F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = TextUnit(14F, TextUnitType.Sp),
        lineHeight = TextUnit(20F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.25F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontSize = TextUnit(12F, TextUnitType.Sp),
        lineHeight = TextUnit(16F, TextUnitType.Sp),
        letterSpacing = TextUnit(0.4F, TextUnitType.Sp),
        fontFamily = NotoSansFontFamily,
        fontWeight = FontWeight.Normal
    ),
)