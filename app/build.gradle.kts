import com.google.protobuf.gradle.*
import com.powilliam.fluffychainsaw.Dependencies
import com.powilliam.fluffychainsaw.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.protobuf")
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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProject" to "true"
                )
            }
        }
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

protobuf {
    protoc {
        artifact = Dependencies.Google.protoc
    }
    generateProtoTasks {
       all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
        }
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

    implementation(Dependencies.AndroidX.Hilt.hilt)
    implementation(Dependencies.AndroidX.Hilt.navigationCompose)
    kapt(Dependencies.AndroidX.Hilt.kapt)

    implementation(Dependencies.AndroidX.Navigation.compose)

    implementation(Dependencies.AndroidX.Room.room)
    implementation(Dependencies.AndroidX.Room.ktx)
    kapt(Dependencies.AndroidX.Room.kapt)

    implementation(Dependencies.Google.protobufJavaLite)
    implementation(Dependencies.AndroidX.DataStore.proto)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.AndroidX.Room.test)
    androidTestImplementation(Dependencies.AndroidX.test)
    androidTestImplementation(Dependencies.AndroidX.espresso)
    androidTestImplementation(Dependencies.AndroidX.Compose.test)
}

kapt {
    correctErrorTypes = true
}
