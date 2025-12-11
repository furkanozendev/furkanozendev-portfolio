plugins {
    id("furkanozendev.plugin.domain")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
