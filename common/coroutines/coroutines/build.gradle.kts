plugins {
    id("kotlin")
}

dependencies {
    implementation(libs.dagger)

    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.stdlib)
}
