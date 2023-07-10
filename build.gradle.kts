import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    val kotlinVersion = "1.9.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("org.jetbrains.dokka") version "1.8.10"

    id("maven-publish")

    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "cloud.drakon"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "2.3.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-java:$ktorVersion")


    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val jvmToolchain = 11

kotlin {
    jvmToolchain(jvmToolchain)
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        configureEach {
            jdkVersion.set(jvmToolchain)
            languageVersion.set("1.9")
        }
    }
}

val dokkaHtml by tasks.getting(DokkaTask::class)
val htmlJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(tasks.dokkaHtml)
    archiveClassifier.set("html-docs")
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
}
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(tasks.dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
}
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/drakon64/KtXivApi")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

kover {
    engine.set(kotlinx.kover.api.DefaultJacocoEngine)
}

sonarqube {
    properties {
        property("sonar.projectKey", "KtXivApi")
        property("sonar.organization", "drakon64")
        property("sonar.host.url", "https://sonarcloud.io")
        property(
            "sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/xml/report.xml"
        )
    }
}
