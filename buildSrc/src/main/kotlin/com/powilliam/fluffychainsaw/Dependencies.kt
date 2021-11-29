package com.powilliam.fluffychainsaw

object Dependencies {
    const val junit = "junit:junit:4.13.2"

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.0"
        const val test = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
            const val tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
            const val foundation =
                "androidx.compose.foundation:foundation:${Versions.composeVersion}"
            const val material = "androidx.compose.material:material:${Versions.composeVersion}"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha01"
            const val icons =
                "androidx.compose.material:material-icons-core:${Versions.composeVersion}"
            const val iconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
            const val test = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
        }

        object Lifecycle {
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}"
        }

        object Hilt {
            const val plugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
            const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
            const val kapt = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:2.4.0-beta02"
        }

        object Room {
            const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
            const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
            const val kapt = "androidx.room:room-compiler:${Versions.roomVersion}"
            const val test = "androidx.room:room-testing:${Versions.roomVersion}"
        }
    }

    object Google {
        const val materialDesign = "com.google.android.material:material:1.5.0-beta01"
    }

    object Accompanist {
        const val navigationMaterial =
            "com.google.accompanist:accompanist-navigation-material:${Versions.accompanistVersion}"
        const val insets =
            "com.google.accompanist:accompanist-insets:${Versions.accompanistVersion}"
        const val insetsUi =
            "com.google.accompanist:accompanist-insets-ui:${Versions.accompanistVersion}"
    }
}