import com.google.cloud.tools.jib.gradle.JibExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib") apply false
    kotlin("jvm") apply false
    kotlin("plugin.noarg") apply false
    kotlin("plugin.spring") apply false
    jacoco
    id("org.sonarqube")
}

repositories {
    mavenCentral()
}

apply(plugin = "jacoco")

subprojects {

    group = "com.greenlight"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }

    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    apply(from = "../gradle/dependency-management.gradle.kts")

    configure<org.jetbrains.kotlin.noarg.gradle.NoArgExtension> {
        annotation("javax.persistence.Entity")
    }

    val testImplementation by configurations
    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
    }

    val springCloudVersion: String by project

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    tasks.withType<JacocoReport> {
        reports {
            html.isEnabled = false
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    /*Just apply for microservices*/
    if (this.name != "common") {
        apply(plugin = "com.google.cloud.tools.jib")
        configure<JibExtension> {
            from {
                image = "azul/zulu-openjdk-alpine:11"

            }
            to {
                image = "gvart/greenlight-${project.name}"
                tags = mutableSetOf("latest")
            }
        }
    }
}


sonarqube {
    properties {
        property("sonar.projectKey", "gvart_green-light")
        property("sonar.organization", "gvart")
        property("sonar.jacoco.reportPaths", "${project.buildDir}/jacoco/report.xml")
        property("sonar.host.url", "https://sonarcloud.io")

    }
}
/**
 * Task to merge all binary (*.exec) files to a global report
 */
tasks {
    register<JacocoReport>("jacocoMergeTest") {
        val classTree = subprojects.map { it.buildDir.resolve("classes/kotlin/main") }
        val sourceTree = subprojects.map { it.projectDir.resolve("src/main/kotlin") }
        val executionTree = subprojects.map { it.buildDir.resolve("jacoco/test.exec") }

        classDirectories.from(classTree)
        sourceDirectories.from(sourceTree)
        executionData(executionTree)

        reports {
            xml.apply {
                isEnabled = true
                destination = File("${project.buildDir}/jacoco/report.xml")
            }
        }
    }

    register("collectJars") {
        doLast {
            val jars = project.fileTree(".") {
                include("**/build/libs/*.jar")
            }
            jars.forEach { file ->
                copy {
                    from(file)
                    into("$rootDir/build/libs")
                }
            }
        }
    }
}
