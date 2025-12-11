plugins {
    id("furkanozendev.plugin.library")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            api(project(":feature:settings:data"))
            api(project(":feature:settings:domain"))
            api(project(":feature:settings:presentation"))
        }
    }
}
