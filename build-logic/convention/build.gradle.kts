import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "furkanozendev.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.org.jetbrains.kotlin.multiplatform.gradle.plugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.compose.plugin)
    compileOnly(libs.kotlin.gradlePlugin)

    implementation(libs.kotlin.serialization.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("furkanozendev.plugin.application") {
            id = "furkanozendev.plugin.application"
            implementationClass = "furkanozendev.plugin.ApplicationConventionPlugin"
        }
        register("furkanozendev.plugin.data") {
            id = "furkanozendev.plugin.data"
            implementationClass = "furkanozendev.plugin.DataConventionPlugin"
        }
        register("furkanozendev.plugin.domain") {
            id = "furkanozendev.plugin.domain"
            implementationClass = "furkanozendev.plugin.DomainConventionPlugin"
        }
        register("furkanozendev.plugin.presentation") {
            id = "furkanozendev.plugin.presentation"
            implementationClass = "furkanozendev.plugin.PresentationConventionPlugin"
        }
        register("furkanozendev.plugin.library") {
            id = "furkanozendev.plugin.library"
            implementationClass = "furkanozendev.plugin.LibraryConventionPlugin"
        }
    }
}
