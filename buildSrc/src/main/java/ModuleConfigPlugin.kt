import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

private const val COMPILE_SDK_VERSION = 31
private const val MIN_SDK_VERSION = 21
private const val TARGET_SDK_VERSION = 31

open class ModuleConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all {
            when (this) {
                is AppPlugin -> {
                    project.extensions
                        .getByType<AppExtension>()
                        .apply { applyCommons() }
                }
                is LibraryPlugin -> {
                    project.extensions
                        .getByType<LibraryExtension>()
                        .apply { applyCommons() }
                }
            }
        }
    }

    private fun BaseExtension.applyCommons() {
        compileSdkVersion(COMPILE_SDK_VERSION)

        defaultConfig.apply {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION

            vectorDrawables.useSupportLibrary = true

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        testOptions.animationsDisabled = true
        testOptions.unitTests.isIncludeAndroidResources = true

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}