// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.4.0" apply false
    id("com.android.library") version "8.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    kotlin("jvm") version "1.9.0"
}