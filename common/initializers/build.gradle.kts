plugins {
    id("com.android.library")

    kotlin("android")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.androidx.startup)
    implementation(libs.androidx.work.runtime)

    implementation(libs.androidx.hilt.work)

    implementation(libs.firebase.crashlytics.library)

    implementation(libs.timber)
}
