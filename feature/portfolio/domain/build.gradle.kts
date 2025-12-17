plugins {
    id("furkanozendev.plugin.domain")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}