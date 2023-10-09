pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AnimeViewApp"
include(":app")
include(":feature:sign-in")
include(":feature:sign-up")
include(":core")
include(":feature:sign-forget-password")
include(":feature:home")
include(":data")
include(":feature:details")
include(":feature:player")
