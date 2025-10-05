// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    // APPLICATION
    alias(libs.plugins.android.application) apply false

    // KOTLIN
    alias(libs.plugins.kotlin.android) apply false

    // COMPOSE
    alias(libs.plugins.kotlin.compose) apply false

    // KOTLIN SERIALIZATION
    alias(libs.plugins.jetbrains.kotlinx.serialization) apply false

    // KSP
    alias(libs.plugins.google.devtools.ksp) apply false
}
