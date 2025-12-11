plugins {
    id("furkanozendev.plugin.application")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":feature:boot"))
            implementation(project(":feature:portfolio"))
            implementation(project(":feature:settings"))
        }
    }
}


