plugins {
    id("furkanozendev.plugin.data")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:domain"))

            implementation(libs.ktor.client.core)
        }
    }
}
