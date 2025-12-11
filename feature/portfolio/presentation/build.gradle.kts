plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:presentation"))
            implementation(project(":core:designsystem"))
            implementation(project(":feature:portfolio:domain"))

            implementation(compose.materialIconsExtended)
            implementation(libs.kotlinx.datetime)
        }
    }
}
