plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.dagger)

    testImplementation(libs.junit.library)
    testImplementation(libs.mockk.library)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.androidx.test.truth)
}
