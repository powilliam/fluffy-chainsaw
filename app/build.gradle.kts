import com.powilliam.fluffychainsaw.Dependencies
import com.powilliam.fluffychainsaw.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.powilliam.fluffychainsaw"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

dependencies {
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.activityCompose)
    implementation(Dependencies.Google.materialDesign)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.tooling)
    implementation(Dependencies.AndroidX.Compose.foundation)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.material3)
    implementation(Dependencies.AndroidX.Compose.icons)
    implementation(Dependencies.AndroidX.Compose.iconsExtended)

    implementation(Dependencies.AndroidX.Lifecycle.viewModelCompose)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.AndroidX.test)
    androidTestImplementation(Dependencies.AndroidX.espresso)
    androidTestImplementation(Dependencies.AndroidX.Compose.test)
}