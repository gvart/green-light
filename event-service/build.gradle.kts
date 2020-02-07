import com.google.cloud.tools.jib.gradle.ContainerParameters
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "0.0.1-SNAPSHOT"
val containerProperties = ContainerParameters().apply {
    jvmFlags = listOf()
}
dependencies {
    compile(project(":common-web"))
    implementation("io.r2dbc:r2dbc-postgresql:0.8.0.RC2")
    implementation("org.springframework.data:spring-data-r2dbc:1.0.0.RELEASE")
    implementation("org.springframework.boot.experimental:spring-boot-starter-r2dbc:0.1.0.M3")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    //used for migration
    implementation("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
}
