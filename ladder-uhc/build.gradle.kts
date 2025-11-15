plugins {
    id("java")
    id("idea")
    id("fr.ladder.releasr") version "1.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.compileJava {
    dependsOn(":ladder-common:shadowJar")

    options.encoding = "UTF-8"
    options.compilerArgs = listOf("-parameters")
}

dependencies {
    implementation(project(":ladder-common"))
    compileOnly("fr.snowtyy", "papermc", "1.8.8")
}

tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}