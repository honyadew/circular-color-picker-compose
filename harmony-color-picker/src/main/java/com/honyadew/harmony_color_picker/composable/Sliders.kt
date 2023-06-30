package com.honyadew.harmony_color_picker.composable

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.honyadew.harmony_color_picker.model.HsvColor
import com.honyadew.harmony_color_picker.model.Positions
import com.honyadew.harmony_color_picker.model.SliderPosition
import com.honyadew.harmony_color_picker.model.SlidersStats

@Composable
internal fun Sliders(
    positions: Positions,
    slidersStats: SlidersStats,
    updatedOnValueChanged: (newValue: HsvColor) -> Unit,
    updatedColor: HsvColor
){
    val vertical = (positions.selected == SliderPosition.START || positions.selected == SliderPosition.END)
    if (positions.brightness == positions.selected)
        HarmonyBar(
            modifier = if (vertical) Modifier.fillMaxHeight() else Modifier.fillMaxWidth(),
            onValueChange = { value ->
                if (slidersStats.enabled){
                    updatedOnValueChanged(updatedColor.copy(value = value))
                }
            },
            currentValue = updatedColor.value,
            color = slidersStats.brightnessColor,
            vertical = vertical,
            paddingValues = slidersStats.brightnessPaddings
        )
    if (positions.alpha == positions.selected)
    //TODO alpha bar
        HarmonyBar(
            modifier = if (vertical) Modifier.fillMaxHeight() else Modifier.fillMaxWidth(),
            onValueChange = { value ->
                if (slidersStats.enabled){
                    updatedOnValueChanged(updatedColor.copy(alpha = value))
                }
            },
            currentValue = updatedColor.alpha,
            color = slidersStats.alphaColor,
            vertical = vertical,
            paddingValues = slidersStats.alphaPaddings
        )

}