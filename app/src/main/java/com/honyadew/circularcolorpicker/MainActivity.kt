package com.honyadew.circularcolorpicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honyadew.harmony_color_picker.HsvColor
import com.honyadew.harmony_color_picker.harmony.ColorHarmonyMode
import com.honyadew.harmony_color_picker.HarmonyColorPicker
import com.honyadew.circularcolorpicker.ui.theme.CircularColorPickerTheme
import com.honyadew.harmony_color_picker.harmony.ColorPickerDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularColorPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HarmonyViewShow()
                }
            }
        }
    }
}

@Preview
@Composable
fun HarmonyViewShow(){
    val hsvColor = remember{ mutableStateOf(HsvColor.DEFAULT)}
    val extractHsvColor = remember{ mutableStateOf(listOf<HsvColor>())}
    val harmonyMode = remember { mutableStateOf(ColorHarmonyMode.NONE)}
    val enabled = remember{ mutableStateOf(true)}

    Column() {
        HarmonyColorPicker(
            harmonyMode = harmonyMode.value,
            value = hsvColor.value,
            onValueChanged = { newValue ->
                hsvColor.value = newValue
                extractHsvColor.value = newValue.getColors(colorHarmonyMode = harmonyMode.value)
            },
            modifier = Modifier.size(384.dp),
            showBrightnessBar = true,
            enabled = enabled.value,
            colors = ColorPickerDefaults.harmonyColors(wheelBorderColor = Color.Black)
        )

        //Logic example
        Button(onClick = {
            val index = ColorHarmonyMode.values().indexOf(harmonyMode.value)
            val all = ColorHarmonyMode.values()
            harmonyMode.value = if (index != all.size-1) all[index + 1] else all[0]
        }) {
            Text(text = "Harmony = ${harmonyMode.value.name}")
        }
        Button(onClick = {
            enabled.value = !enabled.value
        }) {
            Text(text = "Enabled = ${enabled.value}")
        }
        Row {
            Box(modifier = Modifier
                .size(64.dp)
                .background(hsvColor.value.toColor())
            )
            extractHsvColor.value.forEach { hsvColor ->
                Box(modifier = Modifier
                    .size(64.dp)
                    .background(hsvColor.toColor())
                )
            }
        }
    }

//    val hsvColor2 = remember { mutableStateOf(HsvColor.DEFAULT) }
//
//    HarmonyColorPicker(
//        harmonyMode = ColorHarmonyMode.NONE,
//        value = hsvColor2.value,
//        onValueChanged = {newValue ->
//            hsvColor2.value = newValue
//        }
//    )
}

