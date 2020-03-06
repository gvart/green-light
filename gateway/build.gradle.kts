import com.google.cloud.tools.jib.gradle.JibExtension

version = "0.0.1-SNAPSHOT"

configure<JibExtension> {
    container.jvmFlags = listOf()
    to.tags.add(version.toString())
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

}
