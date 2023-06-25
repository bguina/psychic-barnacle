buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
    }
}

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

subprojects {

    repositories {
        google()
        mavenCentral()
    }

    val kotlinVersion by lazy {
        plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper>()
            .map { it.pluginVersion }.distinct().single()
    }
    configurations.forEach { config ->
        config.resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin-")) {
                useVersion(kotlinVersion)
            }
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi,androidx.compose.material3.ExperimentalMaterial3Api"
        kotlinOptions.jvmTarget = "17"
    }
}
