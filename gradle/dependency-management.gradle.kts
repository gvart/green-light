val implementation by configurations
val testContainersVersion = "1.14.0"

dependencies {
    constraints {
        implementation("org.testcontainers:postgresql:$testContainersVersion")
        implementation("org.testcontainers:testcontainers:$testContainersVersion")
        implementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    }
}