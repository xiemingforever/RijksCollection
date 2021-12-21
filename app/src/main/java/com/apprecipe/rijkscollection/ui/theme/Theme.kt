package com.apprecipe.rijkscollection.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
fun RijksCollectionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography(defaultFontFamily = RobotoCondensed),
        content = content
    )
}
