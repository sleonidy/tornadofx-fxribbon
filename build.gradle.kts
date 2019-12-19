plugins {
    kotlin("jvm") version "1.3.61"
}

group = "org.github.sleonidy"
version = "0.1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("no.tornado:tornadofx:1.7.19")
    implementation("com.pixelduke:fxribbon:1.2.1")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}