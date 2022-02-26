import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.Date

buildscript {
    repositories {
        maven("https://maven.minecraftforge.net")
        maven("https://maven.parchmentmc.org")
        mavenCentral()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.+") {
            isChanging = true
        }
        classpath("org.parchmentmc:librarian:1.+")
        classpath("org.spongepowered:mixingradle:0.7.+")
    }
}

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    java
}
apply(plugin="net.minecraftforge.gradle")
apply(plugin="org.parchmentmc.librarian.forgegradle")
apply(plugin="org.spongepowered.mixin")

group = "io.github.samarium150"
version = "0.1.0-alpha.1"

repositories {
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    "minecraft"("net.minecraftforge:forge:1.16.5-36.2.28")
    "annotationProcessor"("org.spongepowered:mixin:0.8.5:processor")
    implementation("thedarkcolour:kotlinforforge:1.16.0")
}

val Project.minecraft: net.minecraftforge.gradle.common.util.MinecraftExtension
    get() = extensions.getByType()

minecraft.let {
    it.mappings("parchment", "2021.10.17-1.16.5")
    it.runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.console.level", "debug")
            mods {
                this.create("mine_chrono_ark") {
                    source(sourceSets.main.get())
                }
            }
        }
        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.console.level", "debug")
            mods {
                this.create("mine_chrono_ark") {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

val Project.mixin: org.spongepowered.asm.gradle.plugins.MixinExtension
    get() = extensions.getByType()

mixin.let {
//    it.add(sourceSets.main.get(), "mine_chrono_ark.mixins.refmap.json")
    it.config("mine_chrono_ark.mixins.json")
    it.setDebug(Pair("verbose", true))
}

tasks {
    val javaVersion = JavaVersion.VERSION_1_8
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        if (JavaVersion.current().isJava9Compatible) {
            options.release.set(8)
        }
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
        }
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    withType<Jar> {
        archiveBaseName.set("mine_chrono_ark")
        manifest {
            attributes(
                mapOf(
                    "Specification-Title" to project.name,
                    "Specification-Vendor" to "Samarium",
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Vendor" to "Samarium",
                    "Implementation-Version" to project.version,
                    "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd").format(Date())
                )
            )
        }
        finalizedBy("reobfJar")
    }
}
