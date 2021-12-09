plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.0-alpha03")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    implementation(gradleApi())
    implementation(localGroovy())
}