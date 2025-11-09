plugins {
    id("java")
    id("idea")
}

group = "fr.sample"

tasks.compileJava {
    options.encoding = "UTF-8"
}

dependencies {
    compileOnly(project(":ladder-uhc"))
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