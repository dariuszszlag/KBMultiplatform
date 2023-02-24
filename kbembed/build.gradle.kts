plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

android {
    namespace = "com.dariusz.kbembed"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
            allVariants()
        }
    }

}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation(project(mapOf("path" to ":kbcore")))
    implementation(project(mapOf("path" to ":kbui")))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
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
    Pair("artifact_id", "kbembed"),
    Pair("version_code", System.getenv("VERSION_CODE")),
    Pair("version_name", System.getenv("VERSION_NAME")),
    Pair("library_name", "KBEmbed"),
    Pair("library_description", "Embed version of KB")
).entries.forEach {
    project.extra.set(it.key, it.value)
}

tasks.withType<PublishToMavenRepository> {
    dependsOn(tasks.assemble)
}