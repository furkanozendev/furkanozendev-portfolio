package furkanozendev.plugin

import furkanozendev.ext.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PresentationConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() =
        with(project.pluginManager) {
            apply(project.libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(project.libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(project.libs.findPlugin("composeCompiler").get().get().pluginId)
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
        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies

        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                webMain {
                    compilerOptions {
                        freeCompilerArgs.add("-Xcontext-parameters")
                    }
                }
                webMain.dependencies {
                    implementation(composeDependencies.runtime)
                    implementation(composeDependencies.foundation)
                    implementation(composeDependencies.material3)
                    implementation(composeDependencies.ui)
                    implementation(composeDependencies.components.resources)
                    implementation(composeDependencies.components.uiToolingPreview)

                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())

                    implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                    implementation(libs.findLibrary("koin-core").get())
                    implementation(libs.findLibrary("koin-compose").get())
                    implementation(libs.findLibrary("koin-compose-viewmodel").get())

                    implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                }
            }
        }
    }
}