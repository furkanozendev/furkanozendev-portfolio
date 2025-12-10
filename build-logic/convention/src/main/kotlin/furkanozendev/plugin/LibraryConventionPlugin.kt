package furkanozendev.plugin

import furkanozendev.ext.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class LibraryConventionPlugin : BaseConventionPlugin() {

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
}