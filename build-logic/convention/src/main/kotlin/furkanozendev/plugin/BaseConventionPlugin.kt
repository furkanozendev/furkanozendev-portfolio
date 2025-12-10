package furkanozendev.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

open class BaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configurePlugin()
            configureWebPlatform()
            configureCommonDependencies()
        }
    }

    open fun Project.configurePlugin() = Unit

    open fun Project.configureWebPlatform() = Unit

    open fun Project.configureCommonDependencies() = Unit
}
