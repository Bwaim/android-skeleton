# android-skeleton
Start android project

## Global configuration

- Use of catalog versions file libs.versions.toml
- Centralize common module configuration in ModuleConfigPlugin

## Plugins

- Use com.diffplug.spotless(https://github.com/diffplug/spotless) plugin to auto format code
- Use com.github.ben-manes.versions(https://github.com/ben-manes/gradle-versions-plugin) to check which dependencies have updates
- Use org.gradle.android.cache-fix(https://github.com/gradle/android-cache-fix-gradle-plugin) to fix problems with the Gradle's build cache feature

## Code

- Coroutines
- Compose for the ui
- Hilt for the dependencies injection
- Theme management
- Crashlytics
- Firebase performance
- LeakCanary

## Initializers

- Manage StrictMode
- Timber initialization
- Coil initialization

## TODO

- Proguard configuration
