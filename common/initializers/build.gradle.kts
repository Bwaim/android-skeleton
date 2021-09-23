plugins {
    id("com.android.library")

    kotlin("android")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.androidx.startup)

    implementation(libs.firebase.crashlytics.library)

    implementation(libs.timber)
}
