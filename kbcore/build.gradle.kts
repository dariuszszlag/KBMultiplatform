plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.dariusz.kbcore"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
            allVariants()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                description = project.extra["library_description"].toString()
                groupId = project.extra["group_id"].toString()
                artifactId = project.extra["artifact_id"].toString()
                version = project.extra["version_name"].toString()
                artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/dariuszszlag/KBMultiplatform")
                credentials {
                    username = System.getenv("GIT_USER")
                    password = System.getenv("GIT_TOKEN")
                }
            }
        }
    }
}

mapOf(
    Pair("group_id", "com.dariusz"),
    Pair("artifact_id", "kbcore"),
    Pair("version_code", System.getenv("VERSION_CODE")),
    Pair("version_name", System.getenv("VERSION_NAME")),
    Pair("library_name", "KBCore"),
    Pair("library_description", "Core of KB")
).entries.forEach {
    project.extra.set(it.key, it.value)
}

tasks.withType<PublishToMavenRepository> {
    dependsOn(tasks.assemble)
}

