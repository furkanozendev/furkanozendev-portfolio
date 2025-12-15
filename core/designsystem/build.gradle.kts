plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }

        webMain.dependencies {
            implementation(compose.materialIconsExtended)

            implementation(libs.kotlinx.datetime)
        }
    }
}