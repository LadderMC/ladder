plugins {
    id("java")
    id("idea")
}

group = "fr.sample"

tasks.compileJava {
    dependsOn(":ladder-uhc:shadowJar")
    options.encoding = "UTF-8"
}

dependencies {
    implementation(project(":ladder-uhc"))
    compileOnly("fr.snowtyy", "papermc", "1.8.8")
}

tasks.processResources {
    filesMatching("**/*.yml") {
        filter {
            it.replace("@version@", version.toString())
        }
    }
    outputs.upToDateWhen { false }
}