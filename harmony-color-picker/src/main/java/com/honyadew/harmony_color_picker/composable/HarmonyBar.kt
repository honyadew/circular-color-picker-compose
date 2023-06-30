package com.honyadew.harmony_color_picker.composable


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection

@Composable
internal fun HarmonyBar(
    onValueChange: (Float) -> Unit,
    currentValue: Float,
    color: Color,
    vertical : Boolean,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    if (!vertical){
        Slider(
            modifier = modifier.padding(paddingValues),
            value = currentValue,
            onValueChange = {
                onValueChange(it)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = color,
                thumbColor = color
            )
        )
    } else {
        VerticalSlider(
            modifier = modifier.padding(
                start = paddingValues.calculateBottomPadding(),
                end = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                top = paddingValues.calculateStartPadding(LayoutDirection.Ltr)
            ),
            value = currentValue,
            onValueChange = {
                onValueChange(it)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = color,
                thumbColor = color
            )
        )
    }
}
