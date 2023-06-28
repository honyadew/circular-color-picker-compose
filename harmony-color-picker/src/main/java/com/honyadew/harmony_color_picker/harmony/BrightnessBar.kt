package com.honyadew.harmony_color_picker.harmony


import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.honyadew.harmony_color_picker.HsvColor

@Composable
internal fun BrightnessBar(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    currentColor: HsvColor,
    color: Color
) {
    Slider(
        modifier = modifier,
        value = currentColor.value,
        onValueChange = {
            onValueChange(it)
        },
        //TODO refactor all colors
        colors = SliderDefaults.colors(
            activeTrackColor = color,
            thumbColor = color
        )
    )
}
