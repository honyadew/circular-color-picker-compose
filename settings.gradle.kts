pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

rootProject.name = "CircularColorPicker"
include(":app")
include(":harmony-color-picker")
