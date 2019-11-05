rootProject.name = "greenlight"

include("config-server", "registry-service", "event-service")


val kotlinVersion: String by settings
val springBootPluginVersion: String by settings
val springDependencyManagementPluginVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootPluginVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementPluginVersion)
            }
        }
    }
}