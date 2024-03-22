// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.0" apply false
    kotlin("plugin.serialization") version "1.6.21"
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}