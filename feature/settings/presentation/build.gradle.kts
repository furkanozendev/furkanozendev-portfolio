plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:presentation"))
            implementation(project(":feature:settings:domain"))
        }
    }
}
