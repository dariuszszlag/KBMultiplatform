plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

group = "com.dariusz"
version = System.getenv("VERSION_NAME")

val GIT_USER: String? by project
val GIT_TOKEN: String? by project

android {
    namespace = "com.dariusz.sdk"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    api("com.dariusz:kbcore-android:0.1.1")
    api("com.dariusz:kbembed:0.1.10")
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.8.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")
}

val sourceJar by tasks.registering(Jar::class) {
    from(android.sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            description = "Sdk version of KB"
            groupId = "com.dariusz"
            artifactId = "sdk"
            version = System.getenv("VERSION_NAME")
            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
            artifact(sourceJar)

            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                //Iterate over the compile dependencies (we don't want the test ones), adding a <dependency> node for each
                configurations.api.get().allDependencies.forEach {
                    if(it.group == groupId) {
                        val dependencyNode = dependenciesNode.appendNode ("dependency")
                        dependencyNode.appendNode("groupId", it.group)
                        dependencyNode.appendNode("artifactId", it.name)
                        dependencyNode.appendNode("version", it.version)
                    }
                }
            }
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
}

tasks.withType<PublishToMavenRepository> {
    dependsOn(tasks.assemble)
}
