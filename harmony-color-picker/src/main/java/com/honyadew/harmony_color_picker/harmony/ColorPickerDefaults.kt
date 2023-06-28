package com.honyadew.harmony_color_picker.harmony

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

object ColorPickerDefaults {

    @Composable
    fun harmonyColors(
        wheelBorderColor: Color = Color.Transparent,
        brightnessBarColor: Color = MaterialTheme.colorScheme.primary,
        primaryMagnifierColor: Color = Color.White,
        additionalMagnifierColor: Color = primaryMagnifierColor,
        disabledWheelBorderColor: Color = Color.Transparent,
        disabledBrightnessBarColor: Color = MaterialTheme.colorScheme.inversePrimary,
        disabledPrimaryMagnifierColor: Color = Color.Gray,
        disabledAdditionalMagnifierColor: Color = disabledPrimaryMagnifierColor
    ): ColorPickerColors = DefaultColorPickerColors(
        wheelBorderColor,
        brightnessBarColor,
        primaryMagnifierColor,
        additionalMagnifierColor,
        disabledWheelBorderColor,
        disabledBrightnessBarColor,
        disabledPrimaryMagnifierColor,
        disabledAdditionalMagnifierColor
    )
}

@Immutable
private class DefaultColorPickerColors(
    private val wheelBorderColor : Color,
    private val brightnessBarColor: Color,
    private val primaryMagnifierColor: Color,
    private val additionalMagnifierColor: Color,
    private val disabledWheelBorderColor : Color,
    private val disabledBrightnessBarColor: Color,
    private val disabledPrimaryMagnifierColor: Color,
    private val disabledAdditionalMagnifierColor: Color,
) : ColorPickerColors {
    @Composable
    override fun wheelBorderColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) wheelBorderColor else disabledWheelBorderColor)
    }
    
    @Composable
    override fun brightnessBarColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) brightnessBarColor else disabledBrightnessBarColor)
    }

    @Composable
    override fun primaryMagnifierColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) primaryMagnifierColor else disabledPrimaryMagnifierColor)
    }

    @Composable
    override fun additionalMagnifierColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) additionalMagnifierColor else disabledAdditionalMagnifierColor)
    }

}

@Stable
interface ColorPickerColors {
    @Composable
    fun wheelBorderColor(enabled: Boolean) : State<Color>
    
    @Composable
    fun brightnessBarColor(enabled: Boolean) : State<Color>
    
    @Composable
    fun primaryMagnifierColor(enabled: Boolean) : State<Color>
    
    @Composable
    fun additionalMagnifierColor(enabled: Boolean) : State<Color>
}