plugins {
    id("com.android.library")

    kotlin("android")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {

    implementation(libs.androidx.activity.ktx)

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.timber)
}
