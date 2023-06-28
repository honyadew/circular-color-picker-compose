#  🎨 Circular Color Piker Compose.

![Maven Central](/*Link TODO*/)

HarmonyColorPicker - Circular wheel with harmony modes

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

## Screnshots

![Screenshot1v3](https://github.com/HonyaDew/circular-color-picker-compose/assets/116727132/f0def2ea-95fb-43df-b70f-68a4735386b3)

![Screenshot2v3](https://github.com/HonyaDew/circular-color-picker-compose/assets/116727132/90465552-17a8-4a76-850d-83f61439b756)

## Minimal usage example

```kotlin
@Composable
fun AnyComposableFunction() {
    val hsvColor = remember { mutableStateOf(HsvColor.DEFAULT) }

    HarmonyColorPicker(
        harmonyMode = ColorHarmonyMode.NONE,
        value = hsvColor.value,
        onValueChanged = { newValue ->
            hsvColor.value = newValue
        }
    )

    Box(Modifier.size(64.dp).background(hsvColor.value.toColor()))
}
```

## Full Usage example

```kotlin
@Composable
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
