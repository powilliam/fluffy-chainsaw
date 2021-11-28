// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version com.powilliam.fluffychainsaw.Versions.androidGradlePluginVersion apply false
    id("com.android.library") version com.powilliam.fluffychainsaw.Versions.androidLibraryVersion apply false
    id("org.jetbrains.kotlin.android") version com.powilliam.fluffychainsaw.Versions.kotlinVersion apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}