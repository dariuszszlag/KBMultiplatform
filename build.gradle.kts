plugins {
    id("com.android.library").version("7.4.1").apply(false)
    id("com.android.application").version("7.4.1").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("plugin.serialization").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)

}


allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    // TODO libs doesn't resolve if we do this
    // apply(plugin = libs.plugins.ktlint.get().pluginId)
/*    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        enableExperimentalRules.set(true)
        verbose.set(true)
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }*/

/*    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.getByName("ktlintCheck"))
        }
    }*/
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
