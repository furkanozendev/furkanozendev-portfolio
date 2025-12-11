plugins {
    id("furkanozendev.plugin.library")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            api(project(":feature:portfolio:data"))
            api(project(":feature:portfolio:domain"))
            api(project(":feature:portfolio:presentation"))
        }
    }
}
