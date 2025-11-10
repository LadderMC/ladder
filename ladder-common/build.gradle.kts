plugins {
    id("java")
    id("idea")
    id("fr.ladder.releasr") version "1.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.compilerArgs = listOf("-parameters")
}

dependencies {
    implementation("org.jetbrains", "annotations", "24.0.1")
    implementation("org.slf4j", "slf4j-simple", "1.6.1")

    implementation("fr.ladder", "wirer", "1.0.0")
    implementation("fr.ladder", "polyglot", "1.0.0")
    compileOnly("fr.snowtyy", "papermc", "1.8.8")
}

tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}