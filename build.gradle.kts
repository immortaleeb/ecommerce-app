import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.versions)
}

tasks.withType<DependencyUpdatesTask> {
    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }

    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

subprojects {
    apply {
        plugin("java-library")
        plugin(rootProject.libs.plugins.kotlin.jvm.get().pluginId)
    }

    repositories {
        mavenCentral()
    }

    val implementation by configurations
    val testImplementation by configurations
    val testRuntimeOnly by configurations

    dependencies {
        implementation(rootProject.libs.bundles.log4j)
        implementation(rootProject.libs.log4j.ecs.layout)

        testImplementation(platform(rootProject.libs.junit.bom))
        testImplementation(rootProject.libs.junit.jupiter.api)
        testRuntimeOnly(rootProject.libs.junit.jupiter.engine)
        testRuntimeOnly(rootProject.libs.junit.platform.launcher)
        testImplementation(rootProject.libs.assertj.core)
    }

    plugins.withId(rootProject.libs.plugins.kotlin.jvm.get().pluginId) {
        configure<KotlinJvmProjectExtension> {
            jvmToolchain(23)
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}