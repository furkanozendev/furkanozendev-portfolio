plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(compose.materialIconsExtended)
        }
    }
}