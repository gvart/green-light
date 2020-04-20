import com.google.cloud.tools.jib.gradle.JibExtension

version = "0.0.1-SNAPSHOT"

configure<JibExtension> {
    container.jvmFlags = listOf()
    to.tags.add(version.toString())
}

dependencies {
    compile(project(":common"))
    implementation("io.r2dbc:r2dbc-postgresql:0.8.0.RC2")

    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation("io.rsocket:rsocket-load-balancer")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    //security
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.security:spring-security-jwt")

    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}