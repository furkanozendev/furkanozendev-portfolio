plugins {
    id("furkanozendev.plugin.data")
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            implementation(project(":core:domain"))

            implementation(libs.ktor.client.core)
        }
    }
}
