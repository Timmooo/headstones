plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
}

group = "tk.alex3025"
version = "1.0.1"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.bg-software.com/repository/api/")
}

dependencies {
    // Paper dev bundle for 1.21.8
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")

    // WildLoadersAPI equivalent
    compileOnly("com.bgsoftware:WildLoadersAPI:1.2.1")
}

val pluginVersion = project.version.toString()

tasks.processResources {
    inputs.property("pluginVersion", pluginVersion)
    filesMatching("plugin.yml") {
        expand(mapOf("version" to pluginVersion))
    }
}

// Optional: include plugin resources like plugin.yml in the JAR
tasks.jar {
    archiveBaseName.set("Headstones")
    archiveVersion.set(project.version.toString())
}

