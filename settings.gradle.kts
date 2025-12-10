pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "furkanozendev"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":composeApp")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:common")
include(":core:designsystem")
include(":feature:boot")
include(":feature:portfolio")
include(":feature:portfolio:data")
include(":feature:portfolio:domain")
include(":feature:portfolio:presentation")
include(":feature:settings")
include(":feature:settings:data")
include(":feature:settings:domain")
include(":feature:settings:presentation")
