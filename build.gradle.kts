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
    testImplementation("org.kordamp.ikonli:ikonli-javafx:2.4.0")
    testImplementation("org.kordamp.ikonli:ikonli-devicons-pack:2.4.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}