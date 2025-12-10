plugins {
    id("furkanozendev.plugin.application")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:boot"))
            implementation(project(":feature:portfolio"))
        }
    }
}


