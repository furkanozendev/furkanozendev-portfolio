plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }

        webMain.dependencies {
            implementation(project(":core:designsystem"))
            implementation(project(":feature:portfolio:domain"))

            implementation(compose.materialIconsExtended)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
