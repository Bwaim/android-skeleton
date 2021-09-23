plugins {
    id("com.android.application")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")

    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    defaultConfig {
        applicationId = "com.bwaim.androidskeleton"

        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    buildTypes {
        debug {
            extra.set("enableCrashlytics", false)

            applicationIdSuffix = ".debug"

            withGroovyBuilder {
                "FirebasePerformance" {
                    invokeMethod("setInstrumentationEnabled", false)
                }
            }
        }
        release {
            extra.set("enableCrashlytics", true)

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":common:compose"))
    implementation(project(":common:theme:theme"))
    implementation(project(":common:initializers"))

    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.firebase.perf.library)

    debugImplementation(libs.leakcanary.library)
    implementation(libs.leakcanary.plumber)
}

apply(plugin = "com.google.gms.google-services")
