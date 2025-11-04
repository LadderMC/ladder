group = "fr.ladder"

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    group = rootProject.group

    repositories {
        mavenCentral()
        maven {
            name = "maven-public"
            url = uri("https://repo.lylaw.fr/repository/maven-public/")
        }
    }
}
