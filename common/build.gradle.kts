version = "0.0.1-SNAPSHOT"

dependencies {
//    todo remove starters and replace with dependencies itself
    compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    compileOnly("org.springframework.cloud:spring-cloud-starter-oauth2")
    compileOnly("org.springframework.boot:spring-boot-starter-webflux")

    compileOnly("io.rsocket:rsocket-transport-netty")
    compileOnly("io.rsocket:rsocket-load-balancer")
    compileOnly("org.springframework:spring-messaging")

    testImplementation("io.projectreactor:reactor-test")
    //kotlin specific
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}


//build a regular jar (not bootable)
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}
tasks.getByName<Jar>("jar") {
    enabled = true
}