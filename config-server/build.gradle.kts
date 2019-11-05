version = "0.0.1-SNAPSHOT"

dependencies {
    val implementation by configurations
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}
repositories {
    mavenCentral()
}