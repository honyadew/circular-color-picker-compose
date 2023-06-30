package com.honyadew.harmony_color_picker.math

import com.honyadew.harmony_color_picker.model.Positions

internal fun weightCount(
    positions : Positions
) : Float{
    var weight = 0.001f
    weight += if(positions.brightness == positions.selected) 0.1f else 0.0f
    weight += if(positions.alpha == positions.selected) 0.1f else 0.0f
    return weight
}