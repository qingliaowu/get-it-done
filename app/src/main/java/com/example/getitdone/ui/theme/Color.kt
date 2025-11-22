package com.example.getitdone.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary Colors (Vibrant Purple/Blue)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Modern Palette
val PrimaryLight = Color(0xFF6C63FF) // Vibrant Purple
val OnPrimaryLight = Color.White
val PrimaryContainerLight = Color(0xFFEADDFF)
val OnPrimaryContainerLight = Color(0xFF21005D)

val SecondaryLight = Color(0xFFFF6584) // Vibrant Pink/Red
val OnSecondaryLight = Color.White
val SecondaryContainerLight = Color(0xFFFFD8E4)
val OnSecondaryContainerLight = Color(0xFF31111D)

val TertiaryLight = Color(0xFF38B6FF) // Sky Blue
val OnTertiaryLight = Color.White

val BackgroundLight = Color(0xFFF8F9FE) // Very light blue-ish grey
val SurfaceLight = Color.White
val OnSurfaceLight = Color(0xFF1C1B1F)

// Dark Palette
val PrimaryDark = Color(0xFFD0BCFF)
val OnPrimaryDark = Color(0xFF381E72)
val PrimaryContainerDark = Color(0xFF4F378B)
val OnPrimaryContainerDark = Color(0xFFEADDFF)

val SecondaryDark = Color(0xFFFFB2BE)
val OnSecondaryDark = Color(0xFF680016)
val SecondaryContainerDark = Color(0xFF920023)
val OnSecondaryContainerDark = Color(0xFFFFD8E4)

val BackgroundDark = Color(0xFF121212) // Deep Dark
val SurfaceDark = Color(0xFF1E1E1E)
val OnSurfaceDark = Color(0xFFE6E1E5)

// Gradients
val PrimaryGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF6C63FF), Color(0xFF38B6FF))
)

val SecondaryGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFFF6584), Color(0xFFFFD166))
)

val SuccessGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF00C9A7), Color(0xFF92FE9D))
)

val CardGradientLight = Brush.linearGradient(
    colors = listOf(Color.White, Color(0xFFF8F9FE))
)
