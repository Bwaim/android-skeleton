plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.0-alpha11")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    implementation(gradleApi())
    implementation(localGroovy())
}