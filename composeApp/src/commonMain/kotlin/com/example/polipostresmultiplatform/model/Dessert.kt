package com.example.polipostresmultiplatform.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.ui.graphics.vector.ImageVector

data class Dessert(
    val id: String,
    val name: String,
    val stock: Int,
    val sold: Int = 0,
    val price: Double = 0.0,
    val imageUri: String? = null,                // <- NUEVO
    val imagePlaceholder: ImageVector = Icons.Default.Cake
)
