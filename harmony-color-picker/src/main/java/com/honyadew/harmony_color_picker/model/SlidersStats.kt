package com.honyadew.harmony_color_picker.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color

internal data class SlidersStats (
    val brightnessPaddings: PaddingValues,
    val brightnessColor: Color,
    val alphaPaddings: PaddingValues,
    val alphaColor: Color,
    val enabled: Boolean
)