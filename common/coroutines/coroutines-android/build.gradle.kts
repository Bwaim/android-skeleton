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
    implementation(project(":common:coroutines:coroutines"))

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)

    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.stdlib)
}
