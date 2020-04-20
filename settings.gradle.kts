rootProject.name = "greenlight"

include(
    "common",
    "registry-config-server",
    "gateway",
    "event-service",
    "user-service",
    "feedback-service",
    "notification-service"
)

val kotlinVersion: String by settings
val springBootPluginVersion: String by settings
val springDependencyManagementPluginVersion: String by settings
val jibPluginVersion: String by settings
val sonarqubeVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.noarg" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootPluginVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementPluginVersion)
                "com.google.cloud.tools.jib" -> useVersion(jibPluginVersion)
                "org.sonarqube" -> useVersion(sonarqubeVersion)
            }
        }
    }
}
