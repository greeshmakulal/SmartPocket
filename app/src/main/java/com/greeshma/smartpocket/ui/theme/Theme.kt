package com.greeshma.smartpocket.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    primary = Color(0xFFD0BCFF),

    onPrimary = Color(0xFF381E72),

    primaryContainer = Color(0xFF4F378B),

    onPrimaryContainer = Color(0xFFEADDFF),

    secondary = Color(0xFFCCC2DC),

    onSecondary = Color(0xFF332D41),

    secondaryContainer = Color(0xFF4A4458),

    onSecondaryContainer = Color(0xFFE8DEF8),

    background = Color(0xFF141218),

    onBackground = Color(0xFFE6E1E9),

    surface = Color(0xFF141218),

    onSurface = Color(0xFFE6E1E9),

    surfaceVariant = Color(0xFF49454F),

    onSurfaceVariant = Color(0xFFCAC4D0)
)
private val LightColorScheme = lightColorScheme(

    primary = PurplePrimary,

    onPrimary = Color.White,

    primaryContainer = PurplePrimaryContainer,

    onPrimaryContainer = Color(0xFF24103F),

    secondary = PurpleSecondary,

    onSecondary = Color.White,

    secondaryContainer = Color(0xFFE8DEF8),

    onSecondaryContainer = Color(0xFF1D192B),

    tertiary = PurpleTertiary,

    onTertiary = Color.White,

    background = BackgroundLight,

    onBackground = TextPrimary,

    surface = SurfaceLight,

    onSurface = TextPrimary,

    surfaceVariant = SurfaceVariantLight,

    onSurfaceVariant = TextSecondary,

    error = ErrorRed,

    errorContainer = ErrorContainer,

)

@Composable
fun SmartPocketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}