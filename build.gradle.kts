import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
    jacoco
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
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    val testImplementation by configurations

    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
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