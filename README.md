#  🎨 Circular Color Piker Compose.

[![Jitpack](https://jitpack.io/v/honyadew/circular-color-picker-compose.svg)](https://jitpack.io/#honyadew/circular-color-picker-compose)

HarmonyColorPicker - Circular wheel with harmony modes.

## How to get started

Add it in your root `build.gradle.kts` at the end of repositories:
```
allprojects{
    repositories {
        ...
        maven("https://jitpack.io")
    }
}
```

Add the dependency to module `build.gradle.kts`.
```
dependencies {
    ...
    implementation("com.github.honyadew:circular-color-picker-compose:<version>)
}
```

## Screnshots

![Screenshot1v3](https://github.com/HonyaDew/circular-color-picker-compose/assets/116727132/f0def2ea-95fb-43df-b70f-68a4735386b3)

![Screenshot2v3](https://github.com/HonyaDew/circular-color-picker-compose/assets/116727132/90465552-17a8-4a76-850d-83f61439b756)
Amount of additional colors: 
"none - 0; 
complementary - 1; 
triadic - 2; 
analogous - 2; 
tetradic - 3; 
monochromatic - 4; 
shades - 4".

## Customization like in Jetpack Compose items
```kotlin
HarmonyColorPicker(
    ...
    colors = ColorPickerDefaults.harmonyColors(wheelBorderColor = Color.Black)
)

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
): ColorPickerColors = DefaultColorPickerColors(...)
```

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

## Full usage example

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
