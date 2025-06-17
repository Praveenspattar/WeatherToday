package com.example.weathertoday.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.weathertoday.R

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

val ZillaSlab = FontFamily(
    Font(R.font.zillaslab_light, FontWeight.Light),
    Font(R.font.zillaslab_regular, FontWeight.Normal),
    Font(R.font.zillaslab_medium, FontWeight.Medium),
    Font(R.font.zillaslab_bold, FontWeight.Bold),
    Font(R.font.zillaslab_semibold, FontWeight.SemiBold),
    Font(R.font.zillaslab_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.zillaslab_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.zillaslab_bolditalic, FontWeight.Bold, FontStyle.Italic)
)

val Karla = FontFamily(
    Font(R.font.karla_italic, FontWeight.Medium),
    Font(R.font.karla_regular, FontWeight.Bold, FontStyle.Normal)
)

@Composable
fun WeatherTodayTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}