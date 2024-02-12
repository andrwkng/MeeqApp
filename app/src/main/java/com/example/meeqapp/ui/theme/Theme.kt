package com.example.meeqapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object Theme {
    val colorLightOffwhite = Color(0xFFfbfcfe)
    val colorOffwhite = Color(0xFFf2f5fa)
    val colorLightGray = Color(0xFFEAEDF8)
    val gray = Color(0xFFdadfeb)
    val veryLightText = Color(0xFF8894af)
    val lightText = Color(0xFF596275)
    val darkText = Color(0xFF303952)
    val colorPink = Color(0xFFf8a5c2)
    val colorDarkPink = Color(0xFFF78FB3)
    val colorLightPink = Color(0xFFFFF3F8)
    val red = Color(0xFFc44569)
    val colorLightBlue = Color(0xFFEDF0FC)
    val veryLightBlue = Color(0xFFF2F4FD)
    val colorBlue = Color(0xFF778beb)
    val colorDarkBlue = Color(0xFF546de5)
    val colorPurple = Color(0xFF8B77AA)
    val colorLightPurple = Color(0xFFEEEBFF)
    val colorYellow = Color(0xFFF7D795)


    val lightOffwhite = Color(0xFFfbfcfe)
    val lightGray = Color(0xFFEAEDF8)
    val darkBlue = Color(0xFF546de5)
}

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MeeqAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}