#  🎨 Circular Color Piker Compose.

![Maven Central](/*Link TODO*/)

HarmonyColorPicker - Circular wheel with harmony modes (ie complementary, triadic, analogous, shades, monochromatic, tetradic)

Color return count(without selected): 
none - 0
complementary - 1
triadic - 2
analogous - 2
tetradic - 3
monochromatic - 4
shades - 4

## How to get started

Add the dependency to your `build.gradle` file:
```
implementation 'TODO<version>'


```
Or

Add the dependency to your `build.gradle.kts` file:
```
implementation("TODO<version>")
```

## Usage example

```kotlin

fun AnyComposableFunction(){
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
}
```

This is just a fork other library, here I add a more customization and remove useless colors
