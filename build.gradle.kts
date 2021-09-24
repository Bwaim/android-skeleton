import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        val libs = project.extensions
            .getByType<VersionCatalogsExtension>()
            .named("libs") as org.gradle.accessors.dm.LibrariesForLibs

        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.google.gms)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.firebase.perf.gradle)
        classpath(libs.hilt.gradle)
    }
}

plugins {
    id("com.diffplug.spotless") version "5.15.1"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("org.gradle.android.cache-fix") version "2.4.4" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            ktlint("0.42.1")
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }

        format("misc") {
            target("**/*.gradle", "**/*.gradle.kts", "**/*.md", "**/.gitignore")
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }

        format("xml") {
            target("**/*.xml")
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    plugins.withType<com.android.build.gradle.BasePlugin> {
        apply(plugin = "org.gradle.android.cache-fix")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.run {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=strict"
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    configurations.all {
        resolutionStrategy {
            exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
        }
    }

    project.pluginManager.apply(ModuleConfigPlugin::class)
}