package furkanozendev.plugin

import furkanozendev.ext.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class DomainConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() =
        with(project.pluginManager) {
            apply(project.libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        }

    @OptIn(ExperimentalWasmDsl::class)
    override fun Project.configureWebPlatform() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            js {
                browser()
                binaries.executable()
            }

            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                browser()
                binaries.executable()
            }
        }
    }

    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                webMain {
                    compilerOptions {
                        freeCompilerArgs.add("-Xcontext-parameters")
                    }
                }
                webMain.dependencies {
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                }
            }
        }
    }
}