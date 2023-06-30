package com.honyadew.harmony_color_picker.model

internal data class Positions (
    val brightness : SliderPosition,
    val alpha : SliderPosition,
    val selected : SliderPosition = SliderPosition.DISABLED
)