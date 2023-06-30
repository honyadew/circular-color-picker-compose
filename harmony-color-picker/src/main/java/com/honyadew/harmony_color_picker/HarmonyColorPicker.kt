package com.honyadew.harmony_color_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.honyadew.harmony_color_picker.model.ColorHarmonyMode
import com.honyadew.harmony_color_picker.model.ColorPickerColors
import com.honyadew.harmony_color_picker.model.ColorPickerDefaults
import com.honyadew.harmony_color_picker.model.ColorPickerPaddings
import com.honyadew.harmony_color_picker.composable.HarmonyColorPickerWithMagnifiers
import com.honyadew.harmony_color_picker.composable.Sliders
import com.honyadew.harmony_color_picker.math.weightCount
import com.honyadew.harmony_color_picker.model.HsvColor
import com.honyadew.harmony_color_picker.model.Positions
import com.honyadew.harmony_color_picker.model.SliderPosition
import com.honyadew.harmony_color_picker.model.SlidersStats


@Composable
fun HarmonyColorPicker(
    harmonyMode: ColorHarmonyMode,
    value: HsvColor,
    onValueChanged: (newValue: HsvColor) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    brightnessBarPosition: SliderPosition = SliderPosition.DISABLED,
    alphaBarPosition: SliderPosition = SliderPosition.DISABLED,
    colors: ColorPickerColors = ColorPickerDefaults.colors(),
    paddings: ColorPickerPaddings = ColorPickerDefaults.paddings()
) {
    val slidersStats = SlidersStats(
        brightnessPaddings = paddings.brightnessBarPaddingValues().value,
        brightnessColor = colors.brightnessBarColor(enabled = enabled).value,
        alphaPaddings = paddings.alphaBarPaddingValues().value,
        alphaColor = colors.alphaBarColor(enabled = enabled).value,
        enabled = enabled
    )

    val positions = Positions(brightnessBarPosition, alphaBarPosition)

    val updatedColor by rememberUpdatedState(value)
    val updatedOnValueChanged by rememberUpdatedState(onValueChanged)

    BoxWithConstraints(modifier.padding(paddings.allPaddingValues().value)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val topPositions = positions.copy(selected = SliderPosition.TOP)
            Column(modifier = Modifier.weight(weightCount(topPositions))){
                Sliders(topPositions, slidersStats, updatedOnValueChanged, updatedColor)
            }
            Row(
                modifier = Modifier.weight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val startPosition = positions.copy(selected = SliderPosition.START)
                Row(modifier = Modifier.weight(weightCount(startPosition))){
                    Sliders(startPosition, slidersStats, updatedOnValueChanged, updatedColor)
                }
                HarmonyColorPickerWithMagnifiers(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(paddings.wheelPaddingValue().value),
                    hsvColor = updatedColor,
                    onColorChanged = { newValue ->
                        updatedOnValueChanged(newValue)
                    },
                    harmonyMode = harmonyMode,
                    colors = colors,
                    enabled = enabled
                )
                val endPositions = positions.copy(selected = SliderPosition.END)
                Row(modifier = Modifier.weight(weightCount(endPositions))){
                    Sliders(endPositions, slidersStats, updatedOnValueChanged, updatedColor)
                }
            }
            val bottomPositions = positions.copy(selected = SliderPosition.BOTTOM)
            Column(modifier = Modifier.weight(weightCount(bottomPositions))){
                Sliders(bottomPositions, slidersStats, updatedOnValueChanged, updatedColor)
            }
        }
    }
}
