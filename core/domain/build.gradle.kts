plugins {
    id("furkanozendev.plugin.domain")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
