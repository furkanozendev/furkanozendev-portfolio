plugins {
    id("furkanozendev.plugin.library")
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            api(project(":feature:settings:data"))
            api(project(":feature:settings:domain"))
            api(project(":feature:settings:presentation"))
        }
    }
}
