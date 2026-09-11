plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.23"
}

group = "tk.alex3025"
version = "1.0.1"

java {
    toolchain {
        // Minecraft 26.1+ (incl. 26.2) requires Java 25 to run; the dev bundle
        // and the server jar you'd deploy against both need this.
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.bg-software.com/repository/api/")
}

dependencies {
    // Paper dev bundle for 26.2. Paper dropped the old "-R0.1-SNAPSHOT" naming
    // in 26.1 in favor of <mcversion>.build.<build>-<status>; ".build.+" tracks
    // the latest published build for this Minecraft version, similar to how
    // "-R0.1-SNAPSHOT" used to resolve.
    paperweight.paperDevBundle("26.2.build.+")

    // WildLoadersAPI equivalent — note: this is unused at compile time (see below),
    // kept only in case you want typed access later instead of pure reflection.
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

