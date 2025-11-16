plugins {
    id("java")
    id("idea")
    id("fr.ladder.releasr") version "1.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "fr.ladder.ladder-uhc"

tasks.compileJava {
    dependsOn(":ladder-uhc:shadowJar")
    dependsOn(":ladder-common:impl:shadowJar")
}

dependencies {
    implementation(project(":ladder-uhc"))
    implementation(project(":ladder-common:impl"))
    compileOnly("fr.snowtyy", "papermc", "1.8.8")
}

tasks.shadowJar {
    archiveBaseName.set("ladder-uhc")
    archiveVersion.set("")
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    archiveBaseName.set("ladder-uhc")
    archiveVersion.set("")
    archiveClassifier.set("")
}

tasks.processResources {
    filesMatching("**/*.yml") {
        expand("version" to version)
    }
    outputs.upToDateWhen { false }
}