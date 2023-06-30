package com.honyadew.harmony_color_picker.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ColorPickerDefaults {
    @Composable
    fun colors(
        alphaBarColor: Color = MaterialTheme.colorScheme.onSurface,
        wheelBorderColor: Color = Color.Transparent,
        brightnessBarColor: Color = MaterialTheme.colorScheme.primary,
        primaryMagnifierColor: Color = Color.White,
        additionalMagnifierColor: Color = primaryMagnifierColor,
        disabledAlphaBarColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
        disabledWheelBorderColor: Color = Color.Transparent,
        disabledBrightnessBarColor: Color = MaterialTheme.colorScheme.inversePrimary,
        disabledPrimaryMagnifierColor: Color = Color.Gray,
        disabledAdditionalMagnifierColor: Color = disabledPrimaryMagnifierColor
    ): ColorPickerColors = DefaultColorPickerColors(
        alphaBarColor,
        wheelBorderColor,
        brightnessBarColor,
        primaryMagnifierColor,
        additionalMagnifierColor,
        disabledAlphaBarColor,
        disabledWheelBorderColor,
        disabledBrightnessBarColor,
        disabledPrimaryMagnifierColor,
        disabledAdditionalMagnifierColor
    )

    @Composable
    fun paddings(
        allPaddingValues: PaddingValues = PaddingValues(8.dp),
        wheelPaddingValues: PaddingValues = PaddingValues(0.dp),
        alphaBarPaddingValues: PaddingValues = PaddingValues(0.dp),
        brightnessBarPaddingValues: PaddingValues = PaddingValues(0.dp),
    ): ColorPickerPaddings = DefaultColorPickerPaddings(
        allPaddingValues,
        wheelPaddingValues,
        alphaBarPaddingValues,
        brightnessBarPaddingValues
    )
}

@Immutable
private class DefaultColorPickerPaddings(
    private val allPaddingValues: PaddingValues,
    private val wheelPaddingValues: PaddingValues,
    private val alphaBarPaddingValues: PaddingValues,
    private val brightnessBarPaddingValues: PaddingValues,
) : ColorPickerPaddings {
    @Composable
    override fun allPaddingValues(): State<PaddingValues> {
        return rememberUpdatedState(allPaddingValues)
    }

    @Composable
    override fun wheelPaddingValue(): State<PaddingValues> {
        return rememberUpdatedState(wheelPaddingValues)
    }

    @Composable
    override fun alphaBarPaddingValues(): State<PaddingValues> {
        return rememberUpdatedState(alphaBarPaddingValues)
    }

    @Composable
    override fun brightnessBarPaddingValues(): State<PaddingValues> {
        return rememberUpdatedState(brightnessBarPaddingValues)
    }

}

@Immutable
private class DefaultColorPickerColors(
    private val alphaBarColor: Color,
    private val wheelBorderColor: Color,
    private val brightnessBarColor: Color,
    private val primaryMagnifierColor: Color,
    private val additionalMagnifierColor: Color,
    private val disabledAlphaBarColor: Color,
    private val disabledWheelBorderColor: Color,
    private val disabledBrightnessBarColor: Color,
    private val disabledPrimaryMagnifierColor: Color,
    private val disabledAdditionalMagnifierColor: Color,
) : ColorPickerColors {
    @Composable
    override fun alphaBarColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled)alphaBarColor else disabledAlphaBarColor)
    }

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
interface ColorPickerPaddings {
    @Composable
    fun allPaddingValues(): State<PaddingValues>

    @Composable
    fun wheelPaddingValue(): State<PaddingValues>

    @Composable
    fun alphaBarPaddingValues() : State<PaddingValues>

    @Composable
    fun brightnessBarPaddingValues(): State<PaddingValues>
}

@Stable
interface ColorPickerColors {
    @Composable
    fun alphaBarColor(enabled: Boolean): State<Color>

    @Composable
    fun wheelBorderColor(enabled: Boolean): State<Color>

    @Composable
    fun brightnessBarColor(enabled: Boolean): State<Color>

    @Composable
    fun primaryMagnifierColor(enabled: Boolean): State<Color>

    @Composable
    fun additionalMagnifierColor(enabled: Boolean): State<Color>
}