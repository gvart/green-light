import com.google.cloud.tools.jib.gradle.JibExtension

version = "0.0.1-SNAPSHOT"

configure<JibExtension> {
    container.jvmFlags = listOf()
    to.tags.add(version.toString())
}

dependencies {
    val implementation by configurations
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}
repositories {
    mavenCentral()
}