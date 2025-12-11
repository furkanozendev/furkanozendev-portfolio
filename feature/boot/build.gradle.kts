plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }

        webMain.dependencies {
            implementation(project(":core:presentation"))
            implementation(project(":core:designsystem"))
        }
    }
}