import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.epam.Daniil_Vodopian"
version = "1.0-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.1.60"

    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
    
}

apply {
    plugin("java")
    plugin("kotlin")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib-jre8", kotlin_version))
    testCompile("junit", "junit", "4.12")
    compile("no.tornado", "tornadofx", "1.7.12")
}


configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8

    sourceSets {
        "main" {
            java {
                srcDirs("src")
                srcDirs("01.iris.data")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
