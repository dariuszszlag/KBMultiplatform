plugins {
    kotlin("android")
    id("com.android.library")
}

group = "com.dariusz"
version = System.getenv("VERSION_NAME")

val GIT_USER: String? by project
val GIT_TOKEN: String? by project

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
/*    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
            allVariants()
        }
    }*/

}

dependencies {
    implementation(project(":kbcore"))
    api(platform("androidx.compose:compose-bom:2023.01.00"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-tooling")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.foundation:foundation")
    api("androidx.compose.material3:material3")
    api("androidx.compose.runtime:runtime")
    api("androidx.compose.runtime:runtime-saveable")
    api("androidx.navigation:navigation-compose:2.5.3")
    api("androidx.activity:activity-compose:1.6.1")
    api("androidx.core:core-ktx:1.9.0")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")
    androidTestApi(platform("androidx.compose:compose-bom:2023.01.00"))
    androidTestApi("androidx.compose.ui:ui-test-junit4")
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")
}

/*publishing {
    publications {
        create<MavenPublication>("maven") {
            description = "Embed version of KB"
            groupId = "com.dariusz"
            artifactId = "kbembed"
            version = System.getenv("VERSION_NAME")
            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
        }
    }
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
}*/

/*
tasks.withType<PublishToMavenRepository> {
    dependsOn(tasks.assemble)
}
*/
