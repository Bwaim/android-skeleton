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

    implementation(libs.androidx.core.library)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    api(libs.coil.library)
}
