pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dariuszszlag/KBMultiplatform")
            credentials {
                username = "dariuszszlag"
                password = "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"
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KBMultiApp"
include(":androidApp")
include(":kbcore")
include(":kbui")
include(":kbembed")
