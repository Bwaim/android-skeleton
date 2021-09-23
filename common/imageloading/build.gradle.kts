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
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    api(libs.coil.library)
}
