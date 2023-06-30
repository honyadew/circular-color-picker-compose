package com.honyadew.harmony_color_picker.composable

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.honyadew.harmony_color_picker.math.colorForPosition
import com.honyadew.harmony_color_picker.model.ColorHarmonyMode
import com.honyadew.harmony_color_picker.model.ColorPickerColors
import com.honyadew.harmony_color_picker.model.HsvColor

@Composable
internal fun HarmonyColorPickerWithMagnifiers(
    hsvColor: HsvColor,
    onColorChanged: (HsvColor) -> Unit,
    harmonyMode: ColorHarmonyMode,
    colors: ColorPickerColors,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val hsvColorUpdated by rememberUpdatedState(hsvColor)
    BoxWithConstraints(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp)
            .wrapContentSize()
            .aspectRatio(1f, matchHeightConstraintsFirst = true)

    ) {
        val updatedOnColorChanged by rememberUpdatedState(onColorChanged)
        val diameterPx by remember(constraints.maxWidth) {
            mutableStateOf(constraints.maxWidth)
        }

        var animateChanges by remember {
            mutableStateOf(false)
        }
        var currentlyChangingInput by remember {
            mutableStateOf(false)
        }

        fun updateColorWheel(newPosition: Offset, animate: Boolean) {
            // Work out if the new position is inside the circle we are drawing, and has a
            // valid color associated to it. If not, keep the current position
            val newColor = colorForPosition(newPosition, IntSize(diameterPx, diameterPx), hsvColorUpdated.value)
            if (newColor != null) {
                animateChanges = animate
                updatedOnColorChanged(newColor)
            }
        }

        val inputModifier = if (enabled) {
            Modifier.pointerInput(diameterPx) {
                awaitEachGesture {
                    val down = awaitFirstDown(false)
                    currentlyChangingInput = true
                    updateColorWheel(down.position, animate = true)
                    drag(down.id) { change ->
                        updateColorWheel(change.position, animate = false)
                        if (change.positionChange() != Offset.Zero) change.consume()
                    }
                    currentlyChangingInput = false
                }
            }
        } else Modifier

        Box(inputModifier.fillMaxSize()) {
            ColorWheel(
                hsvColor = hsvColor,
                diameter = diameterPx,
                colors.wheelBorderColor(enabled).value,
                enabled = enabled
            )
            HarmonyColorMagnifiers(
                diameterPx,
                hsvColor,
                animateChanges,
                currentlyChangingInput,
                harmonyMode,
                colors.primaryMagnifierColor(enabled).value,
                colors.additionalMagnifierColor(enabled).value
            )
        }
    }
}