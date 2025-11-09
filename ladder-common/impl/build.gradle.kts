plugins {
    id("java")
    id("idea")
    id("fr.ladder.releasr") version "1.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "fr.ladder.ladder-common"

dependencies {
    implementation(project(":ladder-common"))
    compileOnly("fr.snowtyy", "papermc", "1.8.8")
}