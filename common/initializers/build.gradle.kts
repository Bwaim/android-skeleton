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

    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.core.library)
    implementation(libs.androidx.startup)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.firebase.crashlytics.library)

    implementation(libs.timber)

    implementation(libs.coil.library)
}
