plugins {
    id("furkanozendev.plugin.presentation")
}

kotlin {
    sourceSets {
        webMain.dependencies {
            implementation(project(":core:presentation"))
            implementation(project(":core:designsystem"))
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.furkanozendev.boot.generated.resources"
    generateResClass = always
}