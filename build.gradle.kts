// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.1.0" apply false
    id ("com.android.library") version "7.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

buildscript {
    repositories {
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")

        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}