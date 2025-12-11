plugins {
    id("furkanozendev.plugin.domain")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:domain"))
        }
    }
}
