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
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.core.library)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.okhttp.library)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit.library)
    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.mockk.library)

    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.runner)
//    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.truth)
}
