plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.dariusz.kbmultiapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.dariusz.kbmultiapp.android"
        minSdk = 26
        targetSdk = 33
        versionCode = System.getenv("VERSION_CODE")?.toInt() ?: 1
        versionName = System.getenv("VERSION_NAME") ?: "1"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation(project(mapOf("path" to ":kbembed")))
}