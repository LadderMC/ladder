plugins {
    id("java")
    id("idea")
    id("fr.ladder.releasr") version "1.0.0"
}

group = "fr.ladder"

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "fr.ladder.releasr")

    version = rootProject.version

    repositories {
        mavenCentral()
        maven {
            name = "maven-public"
            url = uri("https://repo.lylaw.fr/repository/maven-public/")
        }
    }
}
