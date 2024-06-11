plugins {
    `java-library`
    id("io.freefair.lombok") version "8.6"
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

bukkit {
    name = "G5Troll"
    main = "pl.kubag5.g5troll.G5Troll"
    apiVersion = "1.20"
    authors = listOf("kubag5")
    commands {
        register("troll") {
            permission = "g5troll.admin"
        }
    }
}
version = "1.15"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.clojars.org/")
}

dependencies {
    paperweight.paperDevBundle("1.20.3-R0.1-SNAPSHOT")
}