package com.honyadew.harmony_color_picker

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.graphics.Color
import com.honyadew.harmony_color_picker.harmony.ColorHarmonyMode

data class HsvColor(
    val hue: Float, // 0..360
    val saturation: Float, // 0..1
    val value: Float, // 0..1
    val alpha: Float // 0..1
) {

    fun toColor(): Color {
        val rgbColor = android.graphics.Color.HSVToColor(
            floatArrayOf(hue, saturation, value)
        )

        val red = android.graphics.Color.red(rgbColor) / 255f
        val green = android.graphics.Color.green(rgbColor) / 255f
        val blue = android.graphics.Color.blue(rgbColor) / 255f

        return Color(
            red = red,
            green = green,
            blue = blue,
            alpha = alpha
        )
    }

    fun getComplementaryColor(): List<HsvColor> {
        return listOf(
            this.copy(hue = (hue + 180) % 360),
        )
    }

    fun getSplitComplementaryColors(): List<HsvColor> {
        return listOf(
            this.copy(hue = (hue + 150) % 360),
            this.copy(hue = (hue + 210) % 360)
        )
    }

    fun getAnalagousColors(): List<HsvColor> {
        return listOf(
            this.copy(hue = (hue + 30) % 360),
            this.copy(hue = (hue - 30) % 360)
        )
    }

    fun getTriadicColors(): List<HsvColor> {
        return listOf(
            this.copy(hue = (hue + 120) % 360),
            this.copy(hue = (hue + 240) % 360)
        )
    }

    fun getTetradicColors(): List<HsvColor> {
        return listOf(
            this.copy(hue = (hue + 90) % 360),
            this.copy(hue = (hue + 180) % 360),
            this.copy(hue = (hue + 270) % 360)
        )
    }

    fun getMonochromaticColors(): List<HsvColor> {
        return listOf(
            this.copy(saturation = (saturation + 0.2f).mod(1f)),
            this.copy(saturation = (saturation + 0.4f).mod(1f)),
            this.copy(saturation = (saturation + 0.6f).mod(1f)),
            this.copy(saturation = (saturation + 0.8f).mod(1f))
        )
    }

    fun getShadeColors(): List<HsvColor> {
        return listOf(
            this.copy(value = (value - 0.10f).mod(1.0f).coerceAtLeast(0.2f)),
            this.copy(value = (value + 0.55f).mod(1.0f).coerceAtLeast(0.55f)),
            this.copy(value = (value + 0.30f).mod(1.0f).coerceAtLeast(0.3f)),
            this.copy(value = (value + 0.05f).mod(1.0f).coerceAtLeast(0.2f))
        )
    }

    fun getColors(colorHarmonyMode: ColorHarmonyMode): List<HsvColor> {
        return when (colorHarmonyMode) {
            ColorHarmonyMode.NONE -> emptyList()
            ColorHarmonyMode.COMPLEMENTARY -> getComplementaryColor()
            ColorHarmonyMode.ANALOGOUS -> getAnalagousColors()
            ColorHarmonyMode.SPLIT_COMPLEMENTARY -> getSplitComplementaryColors()
            ColorHarmonyMode.TRIADIC -> getTriadicColors()
            ColorHarmonyMode.TETRADIC -> getTetradicColors()
            ColorHarmonyMode.MONOCHROMATIC -> getMonochromaticColors()
            ColorHarmonyMode.SHADES -> getShadeColors()
        }
    }

    companion object {

        val DEFAULT = HsvColor(360f, 1.0f, 1.0f, 1.0f)


        fun from(color: Color): HsvColor {
            return HsvColor(
                color.red,
                color.green,
                color.blue,
                color.alpha
            )
        }

        val Saver: Saver<HsvColor, *> = listSaver(
            save = {
                listOf(
                    it.hue,
                    it.saturation,
                    it.value,
                    it.alpha
                )
            },
            restore = {
                HsvColor(
                    it[0],
                    it[1],
                    it[2],
                    it[3]
                )
            }
        )
    }
}