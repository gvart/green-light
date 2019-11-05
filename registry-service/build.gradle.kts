version = "0.0.1-SNAPSHOT"

dependencies {
    val implementation by configurations
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}
