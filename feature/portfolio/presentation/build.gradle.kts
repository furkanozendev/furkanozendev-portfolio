plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            implementation(project(":core:presentation"))
            implementation(project(":feature:portfolio:domain"))
        }
    }
}
