import com.google.cloud.tools.jib.gradle.ContainerParameters

version = "0.0.1-SNAPSHOT"

val containerProperties = ContainerParameters().apply {
    jvmFlags = listOf()
}
dependencies {
    compile(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.security:spring-security-jwt")

    implementation("org.springframework.boot:spring-boot-starter-rsocket")

    runtime("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")
    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


}
