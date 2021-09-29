plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
}

android {
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":common:navigation"))

    implementation(libs.androidx.navigation.compose)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)
}
