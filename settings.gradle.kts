pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/dariuszszlag/KBMultiplatform")
            credentials {
                username = "dariuszszlag"
                password = "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"//System.getenv("ACCESS_TOKEN")
            }
        }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/dariuszszlag/KBMultiplatform")
            credentials {
                username = "dariuszszlag"
                password = "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"//System.getenv("ACCESS_TOKEN")
            }
        }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KBMultiApp"
include(":androidApp")
include(":kbcore")
include(":kbembed")