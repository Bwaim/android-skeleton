plugins {
    id("com.android.application")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
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

    buildTypes {
        debug {
            extra.set("enableCrashlytics", false)

            applicationIdSuffix = ".debug"

//            withGroovyBuilder {
//                "FirebasePerformance" {
//                    invokeMethod("setInstrumentationEnabled", false)
//                }
//            }
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
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

    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)
//    implementation(libs.androidx.core.library)

//    implementation("androidx.core:core-ktx:1.6.0")
//    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
//    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
//    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
//    implementation("androidx.activity:activity-compose:1.3.1")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
//    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
}