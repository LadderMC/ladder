rootProject.name = "ladder"

include("ladder-common")
include("ladder-common:impl")

include("ladder-uhc")
include("ladder-uhc:impl")

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
