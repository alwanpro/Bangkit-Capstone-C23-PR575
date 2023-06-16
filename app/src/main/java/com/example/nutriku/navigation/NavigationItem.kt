package com.example.nutriku.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val title : String,
    val iconSelected : Painter,
    val iconUnselected : Painter,
    val screen : Screen
)