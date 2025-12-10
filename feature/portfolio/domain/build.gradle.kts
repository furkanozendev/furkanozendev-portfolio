plugins {
    id("furkanozendev.plugin.domain")
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            implementation(project(":core:domain"))
        }
    }
}
