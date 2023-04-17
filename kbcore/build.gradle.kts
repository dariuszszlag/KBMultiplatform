plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("co.touchlab.faktory.kmmbridge") version "0.3.7"
}

group = "com.dariusz"
version = System.getenv("VERSION_NAME")

val GIT_USER: String? by project
val GIT_TOKEN: String? by project

kotlin {

    android {
        publishLibraryVariants("release")
        publishLibraryVariantsGroupedByFlavor = true
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
            isStatic = false
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.ktor:ktor-client-core:2.2.3")
                implementation("io.ktor:ktor-client-mock:2.2.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.3")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-android:2.2.3")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.2.3")
            }
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dariuszszlag/KBMultiplatform")
            credentials {
                username = GIT_USER
                password = GIT_TOKEN
            }
        }
    }
}

tasks.withType<PublishToMavenRepository> {
    dependsOn(tasks.assemble)
}

kmmbridge {
    generateVersion()
    spm()
    mavenPublishArtifacts()
}

fun co.touchlab.faktory.KmmBridgeExtension.generateVersion() {
    versionManager.apply {
        set(object: co.touchlab.faktory.versionmanager.VersionManager {
            override val needsGitTags: Boolean
                get() = false

            override fun getVersion(
                project: Project,
                versionPrefix: String,
                versionWriter: co.touchlab.faktory.versionmanager.VersionWriter
            ): String = System.getenv("VERSION_NAME")

        })
        finalizeValue()
    }
    versionWriter.apply {
        set(co.touchlab.faktory.versionmanager.NoOpVersionWriter)
        finalizeValue()
    }
}
