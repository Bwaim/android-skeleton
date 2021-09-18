import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.hilt.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.run {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=strict"
        }
    }

    project.pluginManager.apply(ModuleConfigPlugin::class)
}