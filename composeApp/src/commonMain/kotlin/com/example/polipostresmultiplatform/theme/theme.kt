package com.example.polipostresmultiplatform.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// Definición de Colores
val DarkBackground = Color(0xFF1C1B1F)
val SurfaceColor = Color(0xFF2A282E)
val PinkAccent = Color(0xFFF48FB1)
val PinkText = Color(0xFFF8BBD0)
val WhiteText = Color(0xFFE6E1E5)
val GreyText = Color(0xFFAAAAAA)

@Composable
fun PoliPostresTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = DarkBackground,
            surface = SurfaceColor,
            primary = PinkAccent,
            onBackground = WhiteText,
            onSurface = WhiteText
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}