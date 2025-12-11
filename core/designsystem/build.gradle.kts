plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(compose.materialIconsExtended)

            implementation(libs.kotlinx.datetime)
        }
    }
}