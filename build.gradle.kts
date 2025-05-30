// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //ksp
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    //id 'com.google.devtools.ksp' version '1.8.0-1.0.8'

    //dagger-hilt
    id("com.google.dagger.hilt.android") version "2.48" apply false
}