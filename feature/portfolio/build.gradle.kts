plugins {
    id("furkanozendev.plugin.library")
}

kotlin {
    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            api(project(":feature:portfolio:data"))
            api(project(":feature:portfolio:domain"))
            api(project(":feature:portfolio:presentation"))
        }
    }
}
