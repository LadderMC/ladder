rootProject.name = "ladder"

include("ladder-common")
include("ladder-api")
include("ladder-core")
include("sample")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            name = "MavenPublic"
            url = uri("https://repo.lylaw.fr/repository/maven-public/")
        }
    }
}