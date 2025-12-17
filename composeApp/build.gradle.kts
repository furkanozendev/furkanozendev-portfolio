plugins {
    id("furkanozendev.plugin.application")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:designsystem"))

            implementation(project(":feature:portfolio"))
        }
    }
}


